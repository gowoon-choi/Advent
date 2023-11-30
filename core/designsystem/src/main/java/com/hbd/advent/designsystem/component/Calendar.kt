package com.hbd.advent.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.R
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.designsystem.util.getGiftResourceId
import com.hbd.advent.designsystem.util.getSantaResourceId
import com.hbd.domain.DomainConst
import com.hbd.domain.model.GiftGift
import com.hbd.domain.model.GiftGiftState
import com.hbd.domain.model.SantaGift
import com.hbd.domain.model.SantaGiftState
import com.hbd.domain.model.UiTheme

@Composable
fun CalendarGift(
    theme: UiTheme,
    gifts: List<GiftGift>,
    onClick: (index: Int) -> Unit
){
    Calendar {
        GiftCalendarItem(theme = theme, state = gifts[it].state, date = it+1) {
            onClick(it)
        }
    }
}

@Composable
fun CalendarSanta(
    theme: UiTheme,
    gifts: List<SantaGift>,
    onClick: (index: Int) -> Unit
){
    Calendar {
        SantaCalendarItem(theme = theme, state = gifts[it].state, date = it+1) {
            onClick(it)
        }
    }
}

@Composable
fun Calendar(
    content: @Composable (index: Int) -> Unit,
){
    LazyVerticalGrid(
        modifier = Modifier.height(1200.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(DomainConst.giftCount-1){
            content(it)
        }
        item(span = { GridItemSpan(3) }){
            content(DomainConst.giftCount-1)
        }
    }
}

@Composable
fun SantaCalendarItem(
    theme: UiTheme,
    state: SantaGiftState,
    date: Int,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    CalendarItem(
        resId = getSantaResourceId(context, theme, date, state),
        theme = theme,
        date = date,
        onClick = onClick
    )
}

@Composable
fun GiftCalendarItem(
    theme: UiTheme,
    state: GiftGiftState,
    date: Int,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    CalendarItem(
        resId = getGiftResourceId(context, theme, date, state),
        theme = theme,
        date = date,
        disabled = state == GiftGiftState.DISABLED,
        onClick = onClick
    )
}

@Composable
fun CalendarItem(
    resId: Int,
    theme: UiTheme,
    date: Int,
    disabled: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            }
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = resId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        CalendarNumber(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 6.dp),
            disabled = disabled,
            color = if (theme == UiTheme.GREEN) AdventTheme.colors.Green800 else AdventTheme.colors.Red800,
            date = date
        )
    }
}

@Composable
fun CalendarNumber(
    modifier: Modifier,
    disabled: Boolean,
    color: Color,
    date: Int
) {
    Box(
        modifier = modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(color)
    ) {
        if (disabled) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.lock),
                contentDescription = null,
                tint = AdventTheme.colors.White
            )
        } else {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = date.toString(),
                style = AdventTheme.typography.Body2.copy(fontWeight = FontWeight(500)),
                color = AdventTheme.colors.White
            )
        }
    }

}

@Preview
@Composable
fun CalendarPreview() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Text(text = "rksk")
            CalendarSanta(theme = UiTheme.GREEN, gifts = List(DomainConst.giftCount){ SantaGift(SantaGiftState.SAVE) }){

            }
        }
    }
}