package com.hbd.advent.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hbd.advent.AdventApp
import com.hbd.advent.home.navigation.homeGraph
import com.hbd.advent.login.navigation.initNicknameGraph
import com.hbd.advent.login.navigation.loginGraph

@Composable
fun AdventNavHost(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = AppRoute.advent){
        composable(AppRoute.advent){
            AdventApp(navController)
        }
        loginGraph(navController)
        initNicknameGraph(navController)
        homeGraph(navController)
    }
}