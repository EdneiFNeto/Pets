package com.pets.ui.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pets.R
import com.pets.ui.theme.robotoLight
import com.pets.ui.theme.robotoRegular
import com.pets.viewmodel.PetsEvent
import com.pets.viewmodel.PetsStatus
import com.pets.viewmodel.PetsUiState
import com.pets.viewmodel.PetsViewModel

@Composable
fun ListCat(
    uiState: PetsUiState,
    id: Int,
    viewModel: PetsViewModel = hiltViewModel()
) {
    val items = viewModel.pageCat.collectAsLazyPagingItems()

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
                    items(items.itemCount) { index ->
                        CardCat(items, index, handleEvent = {
                            viewModel.handleEvent(PetsEvent.OnDetail(it, id))
                        })
                    }

                    items.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item {
                                    LoaderIndicator()
                                }
                            }

                            loadState.append is LoadState.Loading -> {
                                item {
                                    LoaderIndicator()
                                }
                            }

                            loadState.prepend is LoadState.Loading -> {
                                item {
                                    LoaderIndicator()
                                }
                            }
                        }
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
                                contentDescription = "Image pets",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop,
                            )

                            uiState.breeds?.breeds?.forEach {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Text(
                                        text = it.name ?: "",
                                        color = Color.Black,
                                        fontSize = 26.sp,
                                        fontFamily = robotoRegular,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(top = 18.dp),
                                        textAlign = TextAlign.Start
                                    )

                                    Text(
                                        text = "Life Span: ${(it.life_span ?: "")}",
                                        color = Color.Black,
                                        fontSize = 26.sp,
                                        fontFamily = robotoRegular,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(top = 18.dp),
                                        textAlign = TextAlign.End
                                    )
                                }

                                Text(
                                    text = "Temperament: ${(it.temperament ?: "")}",
                                    color = Color.Black,
                                    fontSize = 26.sp,
                                    fontFamily = robotoRegular,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    textAlign = TextAlign.Justify
                                )

                                Text(
                                    text = it.description ?: "",
                                    color = Color.Black,
                                    fontSize = 26.sp,
                                    fontFamily = robotoLight,
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

            PetsStatus.FAIL -> {}
        }
    }
}