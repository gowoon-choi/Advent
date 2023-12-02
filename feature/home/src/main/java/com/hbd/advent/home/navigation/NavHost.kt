package com.hbd.advent.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hbd.advent.home.HomeScreen

fun NavGraphBuilder.homeGraph(
    navHostController: NavHostController
){
    navigation(
        startDestination = HomeNavRoute.home,
        route = HomeNavRoute.graph
    ){
        composable(HomeNavRoute.home){
            HomeScreen(navController = navHostController)
        }
    }
}