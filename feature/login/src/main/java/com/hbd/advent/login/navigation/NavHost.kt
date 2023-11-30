package com.hbd.advent.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hbd.advent.login.join.InitNicknameScreen
import com.hbd.advent.login.login.LoginScreen
import com.hbd.create_calendar.navigation.createCalendarGraph


fun NavGraphBuilder.loginGraph(
    navController: NavHostController
){
    navigation(
        startDestination = LoginNavRoute.login,
        route = LoginNavRoute.graph
    ){
        composable(LoginNavRoute.login){
            LoginScreen(navController = navController)
        }
        initNicknameGraph(navController)
    }
}

fun NavGraphBuilder.initNicknameGraph(
    navController: NavHostController
){
    navigation(
        startDestination = InitNicknameNavRoute.initNickname,
        route = InitNicknameNavRoute.graph
    ){
        composable(InitNicknameNavRoute.initNickname){
            InitNicknameScreen(navController = navController)
        }
        createCalendarGraph(navController)
    }
}