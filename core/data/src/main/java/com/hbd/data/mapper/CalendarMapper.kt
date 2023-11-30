package com.hbd.data.mapper

import com.hbd.data.model.request.calendar.CreateCalendarRequest
import com.hbd.domain.model.Calendar

object CalendarMapper: MapperToData<Calendar, CreateCalendarRequest> {
    override fun Calendar.toData(): CreateCalendarRequest {
        return CreateCalendarRequest(title, theme.name)
    }
}