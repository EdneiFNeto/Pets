package com.pets.ui.components

import android.util.Patterns
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pets.R
import com.pets.ui.theme.robotoLight
import com.pets.ui.theme.robotoRegular
import com.pets.viewmodel.LoginEvent

@Composable
fun FormLoginComponent(
    handleEvent: (event: LoginEvent) -> Unit,
    navigate: NavHostController
) {
    val textEmail = remember { mutableStateOf(TextFieldValue("")) }
    var isErrorEmail by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primaryColor))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .size(120.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Dogs",
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = robotoRegular
            )

            Text(
                text = "Cats",
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
                    .height(120.dp)
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
                .offset(y = (-20).dp)
                .padding(start = 36.dp, end = 36.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                if (!isErrorEmail) {
                    handleEvent(
                        LoginEvent.OnLogin(textEmail.value.text, navigate)
                    )
                } else {
                    isErrorEmail = true
                }
            }) {
            Text(text = "Login", color = Color.White)
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    FormLoginComponent(handleEvent = {}, navigate = rememberNavController())
}