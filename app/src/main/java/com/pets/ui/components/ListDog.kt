package com.pets.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.pets.viewmodel.PetsViewModel

@Composable
fun ListDog(
    viewModel: PetsViewModel = hiltViewModel()
) {
    val items = viewModel.pageDog.collectAsLazyPagingItems()

    LazyColumn {
        items(items.itemCount) { index ->
            CardDog(items, index)
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