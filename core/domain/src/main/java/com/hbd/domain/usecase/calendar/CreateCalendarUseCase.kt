package com.hbd.domain.usecase.calendar

import com.hbd.advent.common.model.Result
import com.hbd.domain.model.SantaCalendar
import com.hbd.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateCalendarUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {
    suspend operator fun invoke(calendar: SantaCalendar): Flow<Result<Nothing>> {
        return calendarRepository.createCalendar(calendar)
    }
}