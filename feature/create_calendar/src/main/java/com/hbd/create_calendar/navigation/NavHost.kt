package com.hbd.create_calendar.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hbd.create_calendar.CreateCalendarLandingScreen


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
    }
}