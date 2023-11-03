package com.pets

import android.hardware.camera2.params.DynamicRangeProfiles
import android.os.Bundle
import android.util.Log
import android.util.Patterns
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.LottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.pets.ui.theme.PetsTheme
import com.pets.ui.theme.albanoRegular
import com.pets.ui.theme.robotoLight
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
            LoginStatus.NONE -> SplashScreenComponent(handleEvent)
            LoginStatus.LOGIN -> LoginComponent(handleEvent)
            LoginStatus.SUCCESS -> ListPetsComponent(handleEvent)
            LoginStatus.LOADER,
            LoginStatus.FAIL -> LoaderComponent(uiState, handleEvent)
        }
    }
}

@Composable
fun ListPetsComponent(handleEvent: (event: LoginEvent) -> Unit) {

}

@Composable
fun LoginComponent(
    handleEvent: (event: LoginEvent) -> Unit
) {
    val textEmail = remember { mutableStateOf(TextFieldValue("")) }
    var isErrorEmail by remember { mutableStateOf(false) }

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
                    .height(200.dp)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                TextFieldComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(vertical = 4.dp, horizontal = 12.dp),
                    text = textEmail,
                    label = "E-mail",
                    isError = isErrorEmail,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    onValueChange = {
                        textEmail.value = it
                        isErrorEmail = if (it.text.isNotEmpty())
                            !Patterns.EMAIL_ADDRESS.matcher(it.text).matches()
                        else
                            false
                    }
                )

                if (isErrorEmail) {
                    Text(
                        text = "Campo e-mail inválido!",
                        color = Color.Red,
                        modifier = Modifier.padding(start = 16.dp),
                        textAlign = TextAlign.Start,
                        fontFamily = robotoLight
                    )
                }
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
                if (!isErrorEmail) {
                    handleEvent(
                        LoginEvent.OnLogin(textEmail.value.text)
                    )
                } else {
                    isErrorEmail = true
                }
            }) {
            Text(text = "Login", color = Color.White)
        }
    }
}

@Composable
private fun TextFieldComponent(
    modifier: Modifier,
    text: MutableState<TextFieldValue>,
    label: String,
    isError: Boolean,
    keyboardOptions: KeyboardOptions,
    onValueChange: (TextFieldValue) -> Unit
) {
    TextField(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        value = text.value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { Text(text = label) },
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            disabledPlaceholderColor = Color.Black,
            disabledTextColor = Color.Black,
            disabledLabelColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            errorTextColor = Color.Black,
            focusedTextColor = Color.Black,
            errorCursorColor = Color.Black,
            errorLabelColor = Color.Black,
            errorPlaceholderColor = Color.Black,
            focusedLabelColor = Color.Black,
            focusedPlaceholderColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            unfocusedTextColor = Color.Black,
            errorSupportingTextColor = Color.Black
        ),
        trailingIcon = {
            if (isError)
                Icon(Icons.Filled.Info, "error", tint = Color.Red)
        },
        isError = isError,
    )
}

@Composable
fun LoaderComponent(
    uiState: LoginUiState,
    handleEvent: (event: LoginEvent) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay((0.5).seconds)
        isVisible = true
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorBgScreen))
    ) {
        val (text, lottie) = createRefs()
        when (uiState.status) {
            LoginStatus.FAIL -> {
                if (isVisible) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 60.dp)
                            .constrainAs(text) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(lottie.top)
                            },
                        text = "Usuário não \nencotrado!",
                        fontSize = 36.sp,
                        color = Color.Red,
                        fontFamily = albanoRegular,
                        textAlign = TextAlign.Center
                    )
                }

                Column(modifier = Modifier.constrainAs(lottie) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }) {
                    LottieComponent(id = R.raw.cat_error, dynamicProperties = null)
                }
            }

            else -> {}
        }
    }

    OnReturnLogin(handleEvent)
}

@Composable
fun SplashScreenComponent(
    handleEvent: (event: LoginEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.colorBgScreen)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val dynamicProperties = rememberLottieDynamicProperties(
            rememberLottieDynamicProperty(
                property = LottieProperty.COLOR_FILTER,
                value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    R.color.white,
                    BlendModeCompat.SRC_ATOP
                ),
                keyPath = arrayOf(
                    "**"
                )
            )
        )

        LottieComponent(id = R.raw.ic_loading, dynamicProperties)
        OnReturnLogin(handleEvent)
    }
}

@Composable
private fun OnReturnLogin(
    handleEvent: (event: LoginEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        delay(5.seconds)
        handleEvent(LoginEvent.OnUpdateStatus(LoginStatus.LOGIN))
    }
}

@Composable
private fun LottieComponent(id: Int, dynamicProperties: LottieDynamicProperties?) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(id))

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        dynamicProperties = dynamicProperties
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginComponent(
        handleEvent = {}
    )
}
