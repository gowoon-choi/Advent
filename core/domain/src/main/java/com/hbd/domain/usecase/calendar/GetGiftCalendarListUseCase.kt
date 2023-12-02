package com.hbd.domain.usecase.calendar

import com.hbd.advent.common.model.Result
import com.hbd.domain.model.GiftCalendar
import com.hbd.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGiftCalendarListUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {
    suspend operator fun invoke(): Flow<Result<List<GiftCalendar>>> {
        return calendarRepository.getGiftCalendarList()
    }
}