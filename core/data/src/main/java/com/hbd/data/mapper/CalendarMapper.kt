package com.hbd.data.mapper

import com.hbd.data.model.request.calendar.CreateCalendarRequest
import com.hbd.data.model.response.calendar.SantaCalendar
import com.hbd.domain.model.UiTheme

object CalendarMapper: MapperToData<com.hbd.domain.model.SantaCalendar, CreateCalendarRequest>, MapperToDomain<SantaCalendar, com.hbd.domain.model.SantaCalendar> {
    override fun com.hbd.domain.model.SantaCalendar.toData(): CreateCalendarRequest {
        return CreateCalendarRequest(title, theme.name)
    }

    override fun SantaCalendar.toDomain(): com.hbd.domain.model.SantaCalendar {
        return com.hbd.domain.model.SantaCalendar(title, UiTheme.valueOf(template), subscriberCount)
    }
}