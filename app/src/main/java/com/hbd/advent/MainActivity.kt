package com.hbd.advent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.navigation.AdventNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdventTheme {
                AdventNavHost()
            }
        }
    }
}

@Composable
fun NavScreen(navController: NavController){
    Surface(Modifier.fillMaxSize().background(Color.White)) {
        Column {
            Button(onClick = { navController.navigate("login") }) {
                Text(text = "go to login")
            }
        }
    }
}