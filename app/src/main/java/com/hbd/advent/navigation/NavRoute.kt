package com.hbd.advent.navigation

import com.hbd.advent.home.navigation.HomeNavRoute
import com.hbd.advent.login.navigation.InitNicknameNavRoute
import com.hbd.advent.login.navigation.LoginNavRoute

object AppRoute{
    const val advent = "advent"
    const val loginGraph = LoginNavRoute.graph
    const val initNicknameGraph = InitNicknameNavRoute.graph
    const val homeGraph = HomeNavRoute.graph
}