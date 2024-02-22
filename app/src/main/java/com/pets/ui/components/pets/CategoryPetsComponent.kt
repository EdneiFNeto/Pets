package com.pets.ui.components.pets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pets.R
import com.pets.ui.components.global.BackHandlerComponent
import com.pets.ui.components.global.TopAppBarComponentState
import com.pets.ui.route.MainScreen
import com.pets.ui.theme.robotoRegular
import com.pets.ui.viewmodel.pets.PetsEvent

@Composable
fun CategoryPetsComponent(
    navHostController: NavHostController,
    topAppBarState: TopAppBarComponentState,
    handleEvent: (PetsEvent) -> Unit
) {
    topAppBarState.apply {
        title.value = ""
        visibility.value = true
        navigationIcon.value = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.Home, null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        actions.value = {
            IconButton(onClick = {
                handleEvent(PetsEvent.OnLogout(navHostController))
            }) {
                Icon(
                    Icons.Filled.ExitToApp,
                    stringResource(R.string.label_exit),
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }

    BackHandlerComponent {}

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primaryColor)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .clickable {
                    navHostController.navigate(MainScreen.Pets.route)
                },
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(colorResource(id = R.color.white))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .padding(horizontal = 4.dp),
                    painter = painterResource(id = R.drawable.ic_dog),
                    contentDescription = null
                )

                Text(
                    text = stringResource(R.string.label_dogs),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = robotoRegular,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}
