package com.hbd.data.remote.datasource

import com.hbd.advent.common.model.Result
import com.hbd.data.model.request.calendar.CreateCalendarRequest
import com.hbd.data.remote.api.CalendarService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CalendarDatasource @Inject constructor(
    private val calendarService: CalendarService
) {
    suspend fun createCalendar(createCalendarRequest: CreateCalendarRequest): Flow<Result<Nothing>> {
        return flow { emit(calendarService.createCalendar(createCalendarRequest)) }.map {
            if (it.isSuccessful) {
                Result.Success()
            } else {
                Result.Error(Exception(it.message()))
            }
        }
    }
}