package com.hbd.advent.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.R
import com.hbd.advent.designsystem.theme.AdventTheme

@Composable
fun ScreenTitle(
    title: String,
    color: Color = AdventTheme.colors.Black
){
    Text(
        text = title,
        style = AdventTheme.typography.H1,
        color = color
    )
}

@Composable
fun ScreenTitleWithSubtitle(
    title: String,
    subtitle: String,
    textAlign: TextAlign? = null,
    horizontalAlign: Alignment.Horizontal = Alignment.Start
){
    Column {
        Text(
            modifier = Modifier.align(horizontalAlign),
            text = title,
            style = AdventTheme.typography.H1,
            textAlign = textAlign
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = subtitle,
            style = AdventTheme.typography.H4,
            color = AdventTheme.colors.Black400,
            textAlign = textAlign
        )
    }
}

@Composable
fun TextWithPersonIcon(
    contentColor: Color, subscriberCount: Int
) {
    Row {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.my_filled),
            contentDescription = null,
            tint = contentColor
        )
        Text(
            text = stringResource(id = R.string.subscriber) + " " + subscriberCount + stringResource(id = R.string.subscriber_unit),
            style = AdventTheme.typography.Body3,
            color = contentColor
        )
    }
}

@Composable
fun CalendarTitleWithGiftCount(
    title: String,
    giftCount: Int,
    textColor: Color,
    countColor: Color
){
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(color = textColor)){
                append(title)
            }
            append("  ")
            withStyle(SpanStyle(color = countColor)){
                append(giftCount.toString())
            }
        },
        style = AdventTheme.typography.H2
    )
}