package com.hbd.advent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.navigation.AdventNavHost
import com.hbd.advent.navigation.AppRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
fun AdventApp(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    Surface(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when (state.route) {
            TargetRoute.LOGIN -> {
                navController.navigate(AppRoute.loginGraph)
            }
            TargetRoute.HOME -> {
                navController.navigate(AppRoute.homeGraph)
            }
        }
    }
}