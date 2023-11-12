package com.hbd.advent.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.R
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.designsystem.util.getGiftResourceId
import com.hbd.advent.designsystem.util.getSantaResourceId
import com.hbd.domain.model.GiftGiftState
import com.hbd.domain.model.SantaGiftState
import com.hbd.domain.model.UiTheme

@Composable
fun Calendar(){
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(24){
            SantaCalendarItem(theme = UiTheme.GREEN, state = SantaGiftState.SAVE, date = it+1) {
                
            }
        }
        item(span = { GridItemSpan(3) }){
            SantaCalendarItem(theme = UiTheme.GREEN, state = SantaGiftState.SAVE, date = 25) {

            }
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
            .background(Color.White)){
        Calendar()
    }
}