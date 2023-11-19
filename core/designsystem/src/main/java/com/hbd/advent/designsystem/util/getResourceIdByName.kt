package com.hbd.advent.designsystem.util

import android.content.Context
import android.content.res.Resources.NotFoundException
import com.hbd.advent.common.util.DecimalFormatUtil
import com.hbd.advent.designsystem.R
import com.hbd.domain.model.GiftGiftState
import com.hbd.domain.model.SantaGiftState
import com.hbd.domain.model.UiTheme

const val fileNamePrefix ="gift"

fun getSantaResourceId(context: Context, theme: UiTheme, date: Int, state: SantaGiftState): Int{
    val themeStr = when(theme){
        UiTheme.GREEN -> "green"
        UiTheme.RED -> "red"
    }
    val stateStr = when(state){
        SantaGiftState.UNSAVE -> "_disaved"
        SantaGiftState.SAVE -> ""
    }
    val dateStr = "_${DecimalFormatUtil.getDateByDigit(date)}"
    val fileName = fileNamePrefix + themeStr + stateStr + dateStr
    return getResourceId(context, fileName)
}

fun getGiftResourceId(context: Context, theme: UiTheme, date: Int, state: GiftGiftState): Int{
    val themeStr = when(theme){
        UiTheme.GREEN -> "green"
        UiTheme.RED -> "red"
    }
    val stateStr = when(state){
        GiftGiftState.OPENED -> "_read"
        else -> ""

    }
    val dateStr = "_${DecimalFormatUtil.getDateByDigit(date)}"
    val fileName = fileNamePrefix + themeStr + stateStr + dateStr
    return getResourceId(context, fileName)
}

fun getResourceId(context: Context, resourceName: String): Int{
    return try {
        context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    } catch (e: NotFoundException){
        return R.drawable.giftgreen_01
    }
}