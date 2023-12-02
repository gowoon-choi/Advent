package com.hbd.data.remote.api

import com.hbd.data.model.request.calendar.CreateCalendarRequest
import com.hbd.data.model.response.AdventResponse
import com.hbd.data.model.response.calendar.SantaCalendarListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CalendarService {
    @POST("/calendar")
    suspend fun createCalendar(@Body createCalendarRequest: CreateCalendarRequest): Response<AdventResponse<Unit>>

    @GET("/calendar/my")
    suspend fun getSantaCalendarList(): Response<AdventResponse<SantaCalendarListResponse>>
}