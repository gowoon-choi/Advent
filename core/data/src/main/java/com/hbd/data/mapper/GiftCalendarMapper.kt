package com.hbd.data.mapper

import com.hbd.data.model.response.calendar.GiftCalendarResponse
import com.hbd.domain.model.GiftCalendar
import com.hbd.domain.model.UiTheme

object GiftCalendarMapper: MapperToDomain<GiftCalendarResponse, GiftCalendar> {
    override fun GiftCalendarResponse.toDomain(): GiftCalendar {
        return GiftCalendar(
            title = title,
            nickname = nickname,
            theme = UiTheme.valueOf(template),
            notOpenedGiftCount = notOpenedCount
        )
    }
}