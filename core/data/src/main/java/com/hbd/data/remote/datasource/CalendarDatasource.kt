package com.hbd.data.remote.datasource

import com.hbd.advent.common.model.Result
import com.hbd.data.model.request.calendar.CreateCalendarRequest
import com.hbd.data.model.response.calendar.SantaCalendar
import com.hbd.data.remote.api.CalendarService
import com.hbd.data.remote.common.AdventResCode
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

    suspend fun getSantaCalendarList(): Flow<Result<List<SantaCalendar>>> {
        return flow { emit(calendarService.getSantaCalendarList()) }.map { response ->
            if(response.isSuccessful){
                response.body()?.let { adventResponse ->
                    if(adventResponse.status.resCode == AdventResCode.HBD200){
                        adventResponse.data?.let { data ->
                            Result.Success(data)
                        } ?: Result.Error(Exception())
                    } else {
                        Result.Error(Exception(adventResponse.status.resMessage))
                    }
                } ?: Result.Error(Exception())
            } else {
                Result.Error(Exception(response.message()))
            }
        }
    }
}