package com.hbd.advent.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbd.advent.common.util.RemainDateCalculator
import com.hbd.advent.designsystem.component.DayBadge
import com.hbd.advent.designsystem.component.HomeAppBar
import com.hbd.advent.designsystem.component.ScreenTitle
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.designsystem.util.calculateCurrentOffsetForPage
import com.hbd.advent.home.component.CalendarCardGift
import com.hbd.advent.home.component.CalendarCardSanta
import com.hbd.advent.home.component.Switch
import com.hbd.domain.model.UiTheme
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.absoluteValue

enum class Mode {
    SANTA, GIFT
}

@Composable
fun HomeScreen() {
    var mode by remember { mutableStateOf(Mode.SANTA) }
    Crossfade(targetState = mode, label = "") { it ->
        when (it) {
            Mode.SANTA -> HomeSantaContent {
                mode = it
            }

            Mode.GIFT -> HomeGiftContent {
                mode = it
            }
        }
    }
}

@Composable
fun HomeSantaContent(onChangedMode: (Mode) -> Unit) {
    HomeContent(
        bgResId = R.drawable.santa_bg,
        mode = Mode.SANTA,
        onClickAdd = { /*TODO*/ },
        onChangedMode = onChangedMode
    )
}

@Composable
fun HomeGiftContent(onChangedMode: (Mode) -> Unit) {
    HomeContent(
        bgResId = R.drawable.gift_bg,
        mode = Mode.GIFT,
        onClickAdd = { /*TODO*/ },
        onChangedMode = onChangedMode
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    bgResId: Int,
    mode: Mode,
    onClickAdd: () -> Unit,
    onChangedMode: (Mode) -> Unit
) {
    // TODO - calendar data class
    val remainDay = "24"
    val theme = UiTheme.GREEN
    val title = "크리스마스가\n얼마 안남았어요."

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 3 }
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = bgResId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Scaffold(
            topBar = {
                HomeAppBar(
                    onClickAdd = onClickAdd,
                    onClickProfile = {
                        // TODO - go to mypage
                    }
                )
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding() + dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
                        bottom = paddingValues.calculateBottomPadding() + dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding)
                    )
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            horizontal = dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding)
                        )
                ) {
                    Column {
                        DayBadge(
                            day = remainDay,
                            color = if (theme == UiTheme.GREEN) AdventTheme.colors.Green200 else AdventTheme.colors.Red200
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        ScreenTitle(
                            title = title,
                            color = if (mode == Mode.SANTA) AdventTheme.colors.White else AdventTheme.colors.Black
                        )
                    }
                    Switch(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        selectedMode = mode,
                        onChangeMode = onChangedMode
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                // TODO SantaMode Empty View
                CalendarPager(pagerState = pagerState, mode = mode)
                Spacer(modifier = Modifier.height(30.dp))
                RemainDateTimeText(
                    modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(36.dp))
                Indicator(
                    modifier = Modifier.align(CenterHorizontally),
                    mode = mode,
                    currentIndex = pagerState.currentPage
                ) {
                    scope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarPager(
    pagerState: PagerState,
    mode: Mode,
    // calendar list
) {
    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fixed(256.dp),
        contentPadding = PaddingValues(horizontal = 68.dp)
    ) {
        val animatedModifier = Modifier.graphicsLayer {
            val pageOffset = pagerState.calculateCurrentOffsetForPage(it)
            val absValue = abs(1 - pageOffset.absoluteValue)
            val scaleValue = 0.7f
            val alphaValue = 0.3f
            scaleY = absValue * (1f - scaleValue) + scaleValue
            scaleX = absValue * (1f - scaleValue) + scaleValue
            translationX = pageOffset * 30f
            alpha = absValue * (1f - alphaValue) + alphaValue
        }
        when (mode) {
            Mode.SANTA -> CalendarCardSanta(
                modifier = animatedModifier,
                title = it.toString(),
                subscriberCount = it
            ) {
                // TODO onClick
            }


            Mode.GIFT -> CalendarCardGift(
                modifier = animatedModifier,
                title = it.toString(),
                from = it.toString()
            ) {
                // TODO onClick
            }
        }
    }

}

@Composable
fun RemainDateTimeText(
    modifier: Modifier
) {
    val remainDay = RemainDateCalculator.getRemainDayAndHour().day.toString()
    val remainTime = RemainDateCalculator.getRemainDayAndHour().hour.toString()
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append(stringResource(id = R.string.remain_datetime_prefix))
            withStyle(SpanStyle(color = AdventTheme.colors.Green200)) {
                append(" ")
                append(remainDay)
                append(stringResource(id = com.hbd.advent.designsystem.R.string.day))
                append(" ")
                append(remainTime)
                append(stringResource(id = com.hbd.advent.designsystem.R.string.time))
                append(" ")
            }
            append(stringResource(id = R.string.remain_datetime_suffix))
        },
        style = AdventTheme.typography.H3,
        color = AdventTheme.colors.Black400
    )
}

@Composable
fun Indicator(
    modifier: Modifier,
    // calendar list,
    mode: Mode,
    currentIndex: Int,
    onChangedCurrentIndex: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(
                        when (mode) {
                            Mode.SANTA -> {
                                if (currentIndex == it) AdventTheme.colors.Black200
                                else AdventTheme.colors.Black500
                            }

                            Mode.GIFT -> {
                                if (currentIndex == it) AdventTheme.colors.Black450
                                else AdventTheme.colors.Black300
                            }
                        }
                    )
                    .clickable { onChangedCurrentIndex(it) }
            )
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen()
}