package com.hbd.data.model.response.calendar

import kotlinx.serialization.Serializable

@Serializable
data class SantaCalendar(
    val id: String,
    val userId: Long,
    val nickname: String,
    val profileImgUrl: String? = null,
    val title: String,
    val template: String,
    val subscriberCount: Int,
    val createdAt: String,
    val updatedAt: String
)
