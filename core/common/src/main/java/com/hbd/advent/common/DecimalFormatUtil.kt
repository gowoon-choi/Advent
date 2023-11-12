package com.hbd.advent.common

import java.text.DecimalFormat

object DecimalFormatUtil {
    val format = DecimalFormat("00")
    fun getDateByDigit(date: Int): String{
        return format.format(date)
    }
}