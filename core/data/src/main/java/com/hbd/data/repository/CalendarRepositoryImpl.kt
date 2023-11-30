package com.hbd.data.repository

import com.hbd.advent.common.model.Result
import com.hbd.data.mapper.CalendarMapper
import com.hbd.data.mapper.CalendarMapper.toData
import com.hbd.data.remote.datasource.CalendarDatasource
import com.hbd.domain.model.Calendar
import com.hbd.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val calendarDatasource: CalendarDatasource
) : CalendarRepository {
    override suspend fun createCalendar(calendar: Calendar): Flow<Result<Nothing>> {
        return calendarDatasource.createCalendar(calendar.toData())
    }

}