package com.hbd.domain.repository

import com.hbd.advent.common.model.Result
import com.hbd.domain.model.Calendar
import kotlinx.coroutines.flow.Flow

interface CalendarRepository {
    suspend fun createCalendar(calendar: Calendar): Flow<Result<Nothing>>
}