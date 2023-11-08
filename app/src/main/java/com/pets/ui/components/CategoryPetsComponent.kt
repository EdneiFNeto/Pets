package com.pets.ui.components

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pets.R
import com.pets.ui.theme.robotoRegular
import com.pets.viewmodel.LoginEvent
import com.pets.viewmodel.Pet

@Composable
fun CategoryPetsComponent(
    handleEvent: (event: LoginEvent) -> Unit
) {
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
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .clickable {
                    handleEvent(LoginEvent.OnListPet(Pet.DOG))
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
                    text = "Dogs",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = robotoRegular,
                    textAlign = TextAlign.Start
                )
            }
        }

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .clickable {
                    handleEvent(LoginEvent.OnListPet(Pet.CAT))
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
                    painter = painterResource(id = R.drawable.ic_card_cat),
                    contentDescription = null
                )

                Text(
                    text = "Cats",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = robotoRegular,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}
