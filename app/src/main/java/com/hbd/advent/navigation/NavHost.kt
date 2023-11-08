package com.hbd.advent.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hbd.advent.NavScreen
import com.hbd.advent.login.navigation.LoginNavHost
import com.hbd.advent.login.navigation.loginRootName

@Composable
fun AdventNavHost(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = appRootName.app){
        composable(appRootName.app){
            NavScreen(navController)
        }
        composable(loginRootName.login){
            LoginNavHost()
        }
    }
}

object appRootName{
    const val app = "advent"
}