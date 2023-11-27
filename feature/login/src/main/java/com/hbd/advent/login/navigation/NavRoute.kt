package com.hbd.advent.login.navigation

import com.hbd.advent.home.navigation.HomeNavRoute
import com.hbd.create_calendar.navigation.CreateCalendarRoute

object LoginNavRoute{
    const val graph = "loginGraph"

    const val login = "login"

    const val initNicknameGraph = InitNicknameNavRoute.graph
    const val createCalendarGraph = CreateCalendarRoute.graph
    const val homeGraph = HomeNavRoute.graph
}

object InitNicknameNavRoute{
    const val graph = "initNicknameGraph"

    const val initNickname = "initNickname"
}