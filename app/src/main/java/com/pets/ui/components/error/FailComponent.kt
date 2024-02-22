package com.pets.ui.components.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pets.R
import com.pets.ui.components.loader.LottieComponent
import com.pets.ui.theme.albanoRegular
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun FailComponent() {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay((0.5).seconds)
        isVisible = true
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primaryColor))
    ) {
        val (text, lottie) = createRefs()
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
            LottieComponent(id = R.raw.cat_error)
        }
    }
}