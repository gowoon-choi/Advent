package com.hbd.advent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.navigation.AdventNavHost
import com.hbd.advent.navigation.AppRoute

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
fun AdventApp(navController: NavController){
    Surface(Modifier.fillMaxSize().background(Color.White)) {
        Column {
            Button(onClick = { navController.navigate(AppRoute.loginGraph) }) {
                Text(text = "go to login")
            }
        }
    }
}