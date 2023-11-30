package com.hbd.advent.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.component.TextWithPersonIcon
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.home.R

@Composable
fun CalendarCardGift(
    modifier: Modifier,
    title: String,
    from: String
) {
    CalendarCard(
        modifier = modifier,
        containerColor = AdventTheme.colors.GreenPrimary,
        title = title,
        subtitle = {
            Text(
                text = stringResource(id = R.string.from_prefix) + from,
                style = AdventTheme.typography.Body3,
                color = AdventTheme.colors.Black600
            )
        },
        btnTitle = stringResource(id = R.string.edit_calendar)
    )
}

@Composable
fun CalendarCardSanta(
    modifier: Modifier,
    title: String,
    subscriberCount: Int
) {
    CalendarCard(
        modifier = modifier,
        containerColor = AdventTheme.colors.Black700,
        title = title,
        subtitle = {
            TextWithPersonIcon(
                contentColor = AdventTheme.colors.Black450,
                subscriberCount = subscriberCount
            )
        },
        btnTitle = stringResource(id = R.string.edit_calendar)
    )
}

@Composable
fun CalendarCard(
    modifier: Modifier,
    containerColor: Color,
    title: String,
    subtitle: @Composable () -> Unit,
    btnTitle: String
) {
    Box(
        modifier = modifier
            .width(256.dp)
            .height(400.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(containerColor)
            .padding(vertical = 35.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.calendar_card_graphic),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = title,
                style = AdventTheme.typography.H2,
                color = AdventTheme.colors.White,
                textAlign = TextAlign.Center
            )
        }
        Column(Modifier.align(BottomCenter)) {
            Box(Modifier.align(CenterHorizontally)) {
                subtitle()
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(AdventTheme.colors.White.copy(0.1f))
                    .padding(vertical = 10.dp, horizontal = 12.dp)
            ) {
                Text(
                    text = btnTitle,
                    style = AdventTheme.typography.H3,
                    color = AdventTheme.colors.Black200
                )

            }
        }
    }
}
