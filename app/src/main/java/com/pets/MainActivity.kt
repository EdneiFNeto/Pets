package com.pets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.pets.ui.theme.PetsTheme
import com.pets.ui.theme.robotoRegular
import com.pets.viewmodel.LoginEvent
import com.pets.viewmodel.LoginStatus
import com.pets.viewmodel.LoginUiState
import com.pets.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainComponent()
                }
            }
        }
    }
}

@Composable
fun MainComponent(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val handleEvent = viewModel::handleEvent

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState.status) {
            LoginStatus.LOADER -> LoaderComponent(handleEvent)
            LoginStatus.NONE -> SplashScreenComponent(handleEvent)
            LoginStatus.GO_TO_LOGIN -> LoginComponent(uiState, handleEvent)
        }
    }
}

@Composable
fun LoginComponent(
    uiState: LoginUiState,
    handleEvent: (event: LoginEvent) -> Unit
) {
    val textPassword by remember { mutableStateOf(TextFieldValue("")) }
    val textEmail by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorBgScreen))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .size(120.dp),
            painter = painterResource(id = R.drawable.ic_cat),
            contentDescription = null
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Pets",
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = robotoRegular
            )

            Text(
                text = "Medicine",
                color = Color.White,
                fontSize = 26.sp,
                fontFamily = robotoRegular
            )
        }

        Card(
            colors = CardDefaults
                .cardColors(colorResource(id = R.color.colorBgTextFieldLogin)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .padding(horizontal = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                TextFieldComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(vertical = 4.dp, horizontal = 12.dp),
                    textEmail,
                    label = "E-mail"
                )

                TextFieldComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(vertical = 4.dp, horizontal = 12.dp),
                    textPassword,
                    label = "Senha"
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .offset(y = (-32).dp)
                .padding(start = 36.dp, end = 36.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            shape = RoundedCornerShape(8.dp),
            onClick = {
            }) {
            Text(text = "Login", color = Color.White)
        }
    }
}

@Composable
private fun TextFieldComponent(
    modifier: Modifier,
    textLogin: TextFieldValue,
    label: String
) {
    var textLogin1 = textLogin
    TextField(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        value = textLogin1,
        onValueChange = {
            textLogin1 = it
        },
        label = { Text(text = label) },
        placeholder = { Text(text = label) },
        colors = TextFieldDefaults.colors(
            disabledPlaceholderColor = Color.Black,
            disabledTextColor = Color.Black,
            disabledLabelColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )
}

@Composable
fun LoaderComponent(
    handleEvent: (event: LoginEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.colorBgScreen))
    ) {
        LottieComponent(id = R.raw.dogs)
        LaunchedEffect(Unit) {
            delay(5.seconds)
            handleEvent(LoginEvent.OnUpdateStatus(LoginStatus.GO_TO_LOGIN))
        }
    }
}

@Composable
fun SplashScreenComponent(
    handleEvent: (event: LoginEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.colorBgScreen))
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            LottieComponent(id = R.raw.dogs)
            LaunchedEffect(Unit) {
                delay(5.seconds)
                handleEvent(LoginEvent.OnUpdateStatus(LoginStatus.GO_TO_LOGIN))
            }
        }
    }
}

@Composable
private fun LottieComponent(id: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(id))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginComponent(
        uiState = LoginUiState(status = LoginStatus.GO_TO_LOGIN),
        handleEvent = {}
    )
}
