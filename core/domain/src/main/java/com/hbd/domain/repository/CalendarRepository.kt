package com.hbd.domain.repository

import com.hbd.advent.common.model.Result
import com.hbd.domain.model.SantaCalendar
import kotlinx.coroutines.flow.Flow

interface CalendarRepository {
    suspend fun createCalendar(calendar: SantaCalendar): Flow<Result<Nothing>>
    suspend fun getSantaCalendarList(): Flow<Result<List<SantaCalendar>>>
}