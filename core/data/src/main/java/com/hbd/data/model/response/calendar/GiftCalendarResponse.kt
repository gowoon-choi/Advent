package com.hbd.data.model.response.calendar

import kotlinx.serialization.Serializable

@Serializable
data class GiftCalendarResponse(
    val id: String,
    val userId: Long,
    val nickname: String,
    val profileImgUrl: String? = null,
    val title: String,
    val notOpenedCount: Int,
    val template: String,
    val createdAt: String,
    val updatedAt: String
)