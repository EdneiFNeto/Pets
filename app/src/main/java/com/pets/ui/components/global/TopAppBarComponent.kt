package com.pets.ui.components.global

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pets.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    state: TopAppBarComponentState
) {
    if (state.visibility.value) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(colorResource(id = R.color.primaryColor)),
            title = {
                Text(
                    text = state.title.value,
                    color = colorResource(id = R.color.white),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            actions = state.actions.value,
            navigationIcon = state.navigationIcon.value,
            modifier = Modifier
        )
    }
}

class TopAppBarComponentState(
    title: String,
    visibility: Boolean,
    actions: @Composable RowScope.() -> Unit,
    navigationIcon: @Composable () -> Unit,
) {
    var title = mutableStateOf(title)
    var visibility = mutableStateOf(visibility)
    var actions = mutableStateOf(actions)
    var navigationIcon = mutableStateOf(navigationIcon)
}

@Composable
fun rememberTopAppBarState(
    title: String = "",
    visibility: Boolean = true,
    actions: @Composable RowScope.() -> Unit = {},
    navigationIcon: @Composable () -> Unit = {}
) = remember {
    TopAppBarComponentState(
        title = title,
        visibility = visibility,
        actions = actions,
        navigationIcon = navigationIcon,
    )
}