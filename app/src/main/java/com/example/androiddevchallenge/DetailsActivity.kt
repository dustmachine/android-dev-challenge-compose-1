package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme

fun launchDetailsActivity(context: Context, item: Pet) {
    context.startActivity(createDetailsActivityIntent(context, item))
}

fun createDetailsActivityIntent(context: Context, item: Pet = Pet("hello","there",R.drawable.gary)): Intent {
    val intent = Intent(context, DetailsActivity::class.java)
    Log.d("AndroidDevChallenge", "Found my doggie: " + item.name)
    return intent
}

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.White) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        Greeting("Android")
    }
}