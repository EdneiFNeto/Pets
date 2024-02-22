package com.pets.ui.components.global

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pets.R
import com.valentinilk.shimmer.shimmer

@Composable
fun ScreenShimmerViewListComponent() {
    val list = (1..5).map { it }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .background(Color.Transparent)
            .shimmer(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        list.forEach { _ ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BoxComponent(modifier = Modifier.weight(2f))
                }
            }
        }
    }
}

@Composable
fun BoxComponent(modifier: Modifier) {
    Box(
        modifier = modifier
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = R.color.secundaryColor))
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenShimmerViewListComponentPreview() {
    ScreenShimmerViewListComponent()
}
