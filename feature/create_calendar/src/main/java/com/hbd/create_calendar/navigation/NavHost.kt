package com.hbd.create_calendar.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hbd.advent.home.navigation.homeGraph
import com.hbd.create_calendar.CreateCalendarSuccessScreen
import com.hbd.create_calendar.CreateCalendarLandingScreen
import com.hbd.create_calendar.CreateCalendarScreen
import com.hbd.create_calendar.CreateCalendarViewModel


fun NavGraphBuilder.createCalendarGraph(
    navController: NavHostController
){
    navigation(
        startDestination = CreateCalendarRoute.createCalendarLanding,
        route = CreateCalendarRoute.graph,
    ){
        lateinit var viewModel: CreateCalendarViewModel
        composable(CreateCalendarRoute.createCalendarLanding){
            viewModel = hiltViewModel()
            CreateCalendarLandingScreen(viewModel, navController)
        }
        composable(CreateCalendarRoute.createCalendar){
            CreateCalendarScreen(viewModel, navController)
        }
        composable(CreateCalendarRoute.createCalendarSuccess){
            CreateCalendarSuccessScreen(navController)
        }
        homeGraph(navController)
    }
}