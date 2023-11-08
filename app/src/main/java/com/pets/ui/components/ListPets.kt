package com.pets.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pets.R
import com.pets.ui.theme.robotoLight
import com.pets.ui.theme.robotoRegular
import com.pets.viewmodel.Pets

@Composable
fun ListPets(pets: List<Pets>) {
    pets.forEach {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(colorResource(id = R.color.white))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(top = 24.dp, start = 8.dp),
                    painter = painterResource(id = R.drawable.ic_dog),
                    contentDescription = null,
                    alignment = Alignment.Center
                )

                Column(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 24.dp)
                ) {
                    Text(
                        text = it.name,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontFamily = robotoRegular,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "${it.age} de Idade",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = robotoLight,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ListPetsPreview() {
    ListPets(
        pets = arrayListOf(
            Pets(name = "Jhon - 1", age = 7),
            Pets(name = "Jhon - 2", age = 7),
            Pets(name = "Jhon - 3", age = 7)
        )
    )
}