package com.hbd.domain.model

data class GiftCalendar(
    val title: String,
    val nickname: String,
    val theme: UiTheme,
    val notOpenedGiftCount: Int,
)
