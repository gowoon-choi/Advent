package com.hbd.data.model.request.calendar

import kotlinx.serialization.Serializable

@Serializable
data class CreateCalendarRequest(
    val title: String,
    val template: String
)