package com.hbd.data.mapper

import com.hbd.data.model.request.calendar.CreateCalendarRequest
import com.hbd.data.model.response.calendar.SantaCalendarResponse
import com.hbd.domain.model.SantaCalendar
import com.hbd.domain.model.UiTheme

object SantaCalendarMapper: MapperToData<SantaCalendar, CreateCalendarRequest>, MapperToDomain<SantaCalendarResponse, com.hbd.domain.model.SantaCalendar> {
    override fun SantaCalendar.toData(): CreateCalendarRequest {
        return CreateCalendarRequest(title, theme.name)
    }

    override fun SantaCalendarResponse.toDomain(): SantaCalendar {
        return SantaCalendar(title, UiTheme.valueOf(template), subscriberCount)
    }
}