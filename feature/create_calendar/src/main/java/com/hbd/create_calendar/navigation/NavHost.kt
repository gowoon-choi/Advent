package com.hbd.create_calendar.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hbd.advent.home.navigation.homeGraph
import com.hbd.create_calendar.CreateCalendarSuccessScreen
import com.hbd.create_calendar.CreateCalendarLandingScreen
import com.hbd.create_calendar.CreateCalendarScreen


fun NavGraphBuilder.createCalendarGraph(
    navController: NavHostController
){
    navigation(
        startDestination = CreateCalendarRoute.createCalendarLanding,
        route = CreateCalendarRoute.graph
    ){
        composable(CreateCalendarRoute.createCalendarLanding){
            CreateCalendarLandingScreen(navController)
        }
        composable(CreateCalendarRoute.createCalendar){
            CreateCalendarScreen(navController)
        }
        composable(CreateCalendarRoute.createCalendarSuccess){
            CreateCalendarSuccessScreen(navController)
        }
        homeGraph(navController)
    }
}