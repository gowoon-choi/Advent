package com.hbd.data.remote.api

import com.hbd.data.model.request.calendar.CreateCalendarRequest
import com.hbd.data.model.response.AdventResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CalendarService {
    @POST("/calender")
    suspend fun createCalendar(@Body createCalendarRequest: CreateCalendarRequest): Response<AdventResponse<Nothing>>
}