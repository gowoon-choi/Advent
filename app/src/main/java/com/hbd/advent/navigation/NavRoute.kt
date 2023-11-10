package com.hbd.advent.navigation

import com.hbd.advent.login.navigation.LoginNavRoute
import com.hbd.create_calendar.navigation.CreateCalendarRoute

object AppRoute{
    const val advent = "advent"
    const val loginGraph = LoginNavRoute.graph
    const val createCalendarGraph = CreateCalendarRoute.graph
    const val home = "home"
}