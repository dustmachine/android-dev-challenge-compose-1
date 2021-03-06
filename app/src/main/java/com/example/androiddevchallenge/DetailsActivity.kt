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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme

private const val PET_NAME = "PET_NAME"

fun launchDetailsActivity(context: Context, item: Pet) {
    context.startActivity(createDetailsActivityIntent(context, item))
}

fun createDetailsActivityIntent(context: Context, item: Pet = Pet("hello", "there", R.drawable.gary)): Intent {
    val intent = Intent(context, DetailsActivity::class.java)
    intent.putExtra(PET_NAME, item.name)
    Log.d("AndroidDevChallenge", "Found my doggie: " + item.name)
    return intent
}

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val petName = intent.getStringExtra(PET_NAME) + ""

        val petImage = getPetPhoto(petName)

        setContent {
            MyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.White) {
                    PetDetail(petName, petImage)
                }
            }
        }
    }

    private fun getPetPhoto(petName: String): Int {
        return when (petName) {
            "Gary" -> R.drawable.gary
            "Russell" -> R.drawable.russell
            "Spike" -> R.drawable.spike
            "Steve" -> R.drawable.steve
            "Buddy" -> R.drawable.buddy
            "Bingo" -> R.drawable.bingo
            "Morris" -> R.drawable.morris
            else -> R.drawable.gary
        }
    }
}

@Composable
fun PetDetail(name: String, petImage: Int) {
    Row {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(50.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(petImage),
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
            )
            Column {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "This is $name! He is the most " +
                        "adorable doggo you will ever see.",
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Call ForeverHome Pet Adoption Service today!",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        PetDetail("Gary", R.drawable.gary)
    }
}
