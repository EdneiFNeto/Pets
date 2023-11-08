package com.pets.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun LottieComponent(id: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(id))

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}