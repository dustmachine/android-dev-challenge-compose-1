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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var pets = { "onepet" }
            PetList(
                pets = listOf(
                    Pet("Russell", "a super doggo", R.drawable.russell),
                    Pet("Steve", "a silly doggo", R.drawable.steve),
                    Pet("Spike", "an energetic doggo", R.drawable.spike),
                    Pet("Buddy", "a nice doggo", R.drawable.buddy),
                    Pet("Morris", "a sweet doggo", R.drawable.morris)
                )
            )
        }
    }
}

@Composable
fun PetList(pets: List<Pet>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = pets) { pet ->
            PetCard(pet = pet)
            TabRowDefaults.Divider(color = Color.Black, thickness = 3.dp)
        }
    }
}

// Start building your app here!
@Composable
fun PetCard(
    modifier: Modifier = Modifier,
    pet: Pet
) {
    Surface(color = MaterialTheme.colors.background) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .background(color = Color.White)
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
                Pet("Russell", "a super doggo", R.drawable.russell),
                Pet("Steve", "a silly doggo", R.drawable.steve),
                Pet("Spike", "an energetic doggo", R.drawable.spike),
                Pet("Buddy", "a nice doggo", R.drawable.buddy),
                Pet("Morris", "a sweet doggo", R.drawable.morris)
            )
        )
    }
}
//
//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        NewsStory()
//    }
//}
