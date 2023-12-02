package com.hbd.data.repository

import com.hbd.advent.common.model.Result
import com.hbd.data.mapper.CalendarMapper.toData
import com.hbd.data.mapper.CalendarMapper.toDomain
import com.hbd.data.remote.datasource.CalendarDatasource
import com.hbd.domain.model.SantaCalendar
import com.hbd.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val calendarDatasource: CalendarDatasource
) : CalendarRepository {
    override suspend fun createCalendar(calendar: SantaCalendar): Flow<Result<Nothing>> {
        return calendarDatasource.createCalendar(calendar.toData())
    }

    override suspend fun getSantaCalendarList(): Flow<Result<List<SantaCalendar>>> {
        return calendarDatasource.getSantaCalendarList().map {
            when(it){
                is Result.Success -> {
                    Result.Success(it.data?.calendarList?.map { santaCalendar ->  santaCalendar.toDomain() })
                }
                is Result.Error -> {
                    Result.Error(Exception())
                }
            }
        }
    }

}