package com.hbd.advent.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hbd.advent.NavScreen
import com.hbd.advent.login.LoginScreen

@Composable
fun AdventNavHost(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = "app"){
        composable("app"){
            NavScreen(navController)
        }
        composable("login"){
            LoginScreen()
        }
    }

}