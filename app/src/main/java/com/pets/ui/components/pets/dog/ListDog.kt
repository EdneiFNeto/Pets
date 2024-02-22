package com.pets.ui.components.pets.dog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pets.R
import com.pets.data.api.DogResponse
import com.pets.ui.components.error.FailComponent
import com.pets.ui.components.global.ScreenShimmerViewListComponent
import com.pets.ui.theme.albanoRegular
import com.pets.ui.theme.robotoLight
import com.pets.ui.theme.robotoRegular
import com.pets.ui.viewmodel.pets.PetsEvent
import com.pets.ui.viewmodel.pets.PetsStatus
import com.pets.ui.viewmodel.pets.PetsUiState
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun ListDog(
    uiState: PetsUiState,
    handleEvent: (PetsEvent) -> Unit
) {

    val dogs: List<DogResponse> = uiState.dogResponse
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primaryColor))
    ) {
        when (uiState.status) {
            PetsStatus.NONE -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1)
                ) {
                    items(dogs.size) { index ->
                        CardDog(dogs, index, handleEvent = {
                            handleEvent(PetsEvent.OnDetail(dogs[index]))
                        })
                    }
                }
            }

            PetsStatus.DETAIL -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(colorResource(id = R.color.primaryColor))
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(uiState.breeds?.url)
                                    .placeholder(R.drawable.ic_loading)
                                    .build(),
                                contentDescription = stringResource(id = R.string.label_pets),
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .height(250.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp)),
                            )

                            uiState.breeds?.breeds?.forEach {
                                Text(
                                    text = it.name ?: "",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = robotoRegular,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 18.dp),
                                    textAlign = TextAlign.Start
                                )

                                Text(
                                    text = "Life Span: ${(it.life_span ?: "")}",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = robotoLight,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 18.dp),
                                    textAlign = TextAlign.End
                                )

                                Text(
                                    text = "Temperament: ${(it.temperament ?: "")}",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = albanoRegular,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    textAlign = TextAlign.Justify
                                )

                                Text(
                                    text = it.description ?: "",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    textAlign = TextAlign.Justify
                                )
                            }
                        }
                    }
                }
            }

            PetsStatus.FAIL -> {
                LaunchedEffect(Unit) {
                    delay((2).seconds)
                    handleEvent(PetsEvent.OnBackList)
                }

                FailComponent()
            }

            PetsStatus.LOADER -> {
                ScreenShimmerViewListComponent()
            }
        }
    }
}