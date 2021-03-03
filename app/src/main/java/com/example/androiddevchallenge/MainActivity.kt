/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme

typealias OnPetItemClicked = (Pet) -> Unit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetList(
                pets = listOf(
                    Pet("Gary", "a special doggo", R.drawable.gary),
                    Pet("Russell", "a super doggo", R.drawable.russell),
                    Pet("Spike", "an energetic doggo", R.drawable.spike),
                    Pet("Steve", "a silly doggo", R.drawable.steve),
                    Pet("Buddy", "a nice doggo", R.drawable.buddy),
                    Pet("Bingo", "a smart doggo", R.drawable.bingo),
                    Pet("Morris", "a sweet doggo", R.drawable.morris)
                ),
                onPetClicked = { launchDetailsActivity(context = this, item = it) }
            )
        }
    }
}

@Composable
fun PetList(pets: List<Pet>, modifier: Modifier = Modifier, onPetClicked: OnPetItemClicked) {
    LazyColumn(modifier = modifier) {
        items(items = pets) { pet ->
            PetCard(pet = pet, onPetClicked = onPetClicked)
            TabRowDefaults.Divider(color = Color.Black, thickness = 3.dp)
        }
    }
}

// Start building your app here!
@Composable
fun PetCard(
    pet: Pet,
    onPetClicked: OnPetItemClicked
) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Green else Color.White)

    Surface(color = MaterialTheme.colors.background) {
        Row(
            modifier = Modifier
                .background(color = backgroundColor)
                .padding(10.dp)
                .fillMaxWidth()
                .clickable(onClick = {
                    onPetClicked(pet)
                    isSelected = !isSelected
                })
        ) {
            Image(
                painter = painterResource(pet.photo),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
            )
            Column(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Text(text = "Name : ${pet.name}")
                Text(text = "Breed : ${pet.breed}")
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        PetList(
            pets = listOf(
                Pet("Gary", "a special doggo", R.drawable.gary),
                Pet("Russell", "a super doggo", R.drawable.russell),
                Pet("Spike", "an energetic doggo", R.drawable.spike),
                Pet("Steve", "a silly doggo", R.drawable.steve),
                Pet("Buddy", "a nice doggo", R.drawable.buddy),
                Pet("Bingo", "a smart doggo", R.drawable.bingo),
                Pet("Morris", "a sweet doggo", R.drawable.morris)
            ),
            onPetClicked = { }
        )
    }
}
//
// @Preview("Dark Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        NewsStory()
//    }
// }
