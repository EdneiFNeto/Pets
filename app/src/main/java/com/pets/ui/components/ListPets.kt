package com.pets.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pets.R
import com.pets.service.response.DogResponse
import com.pets.viewmodel.PetsViewModel

@Composable
fun ListPets(
    viewModel: PetsViewModel = hiltViewModel()
) {
    val items = viewModel.pager.collectAsLazyPagingItems()

    LazyColumn {
        items(items.itemCount) { index ->
            Pets(items, index)
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

@Composable
private fun LoaderIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun Pets(
    items: LazyPagingItems<DogResponse>,
    index: Int
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            items[index]?.url?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it)
                        .placeholder(R.drawable.ic_loading)
                        .build(),
                    contentDescription = "Image pets",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}