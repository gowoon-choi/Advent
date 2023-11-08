package com.hbd.advent.login.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hbd.advent.login.join.InitNicknameScreen
import com.hbd.advent.login.login.LoginScreen

@Composable
fun LoginNavHost(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = loginRootName.login){
        composable(loginRootName.login){
            LoginScreen(navController)
        }
        composable(loginRootName.initNickname){
            InitNicknameScreen(navController)
        }
    }

}

object loginRootName{
    const val login = "login"
    const val initNickname = "initNickname"
}