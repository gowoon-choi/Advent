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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hbd.advent.common.util.RemainDateCalculator
import com.hbd.advent.designsystem.component.DayBadge
import com.hbd.advent.designsystem.component.HomeAppBar
import com.hbd.advent.designsystem.component.ScreenTitle
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.designsystem.util.calculateCurrentOffsetForPage
import com.hbd.advent.home.component.CalendarCardGift
import com.hbd.advent.home.component.CalendarCardSanta
import com.hbd.advent.home.component.CalendarCardSantaEmpty
import com.hbd.advent.home.component.Switch
import com.hbd.advent.home.navigation.HomeNavRoute
import com.hbd.domain.model.UiTheme
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.absoluteValue

enum class Mode {
    SANTA, GIFT
}

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    var mode by remember { mutableStateOf(Mode.SANTA) }
    Crossfade(targetState = mode, label = "") { it ->
        when (it) {
            Mode.SANTA -> HomeSantaContent(navController = navController) {
                mode = it
            }

            Mode.GIFT -> {
//                HomeGiftContent {
//                    mode = it
//                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeSantaContent(
    viewModel: HomeSantaViewModel = hiltViewModel(),
    navController: NavHostController,
    onChangedMode: (Mode) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState { state.calendarList.size }
    var currentTheme by remember { mutableStateOf(UiTheme.GREEN) }
    HomeContent(
        bgResId = R.drawable.santa_bg,
        mode = Mode.SANTA,
        message = stringResource(id = R.string.santa_main_message),
        badgeContent = {
            DayBadge(
                day = state.dday.toString(),
                color = if (currentTheme == UiTheme.GREEN) AdventTheme.colors.Green200 else AdventTheme.colors.Red200
            )
        },
        pagerContent = {
            CalendarPager(pagerState = pagerState) { modifier, index ->
                CalendarCardSanta(
                    modifier = modifier,
                    title = state.calendarList[index].title,
                    subscriberCount = state.calendarList[index].subscriberCount
                ) {
                    // TODO onClick
                }
            }
        },
        pagerState = pagerState,
        onClickAdd = {
            // TODO
        },
        onChangedMode = onChangedMode
    )
    LaunchedEffect(pagerState.currentPage) {
        if(state.calendarList.isNotEmpty()){currentTheme = state.calendarList[pagerState.currentPage].theme
            currentTheme = state.calendarList[pagerState.currentPage].theme
        }
    }
}

//@Composable
//fun HomeGiftContent(onChangedMode: (Mode) -> Unit) {
//    HomeContent(
//        bgResId = R.drawable.gift_bg,
//        mode = Mode.GIFT,
//        onClickAdd = { /*TODO*/ },
//        onChangedMode = onChangedMode
//    )
//                CalendarCardGift(
//                    modifier = animatedModifier,
//                    title = it.toString(),
//                    from = it.toString()
//                ) {
//                    // TODO onClick
//                }
//}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    bgResId: Int,
    mode: Mode,
    message: String,
    badgeContent: @Composable () -> Unit,
    pagerContent: @Composable () -> Unit,
    pagerState: PagerState,
    onClickAdd: () -> Unit,
    onChangedMode: (Mode) -> Unit
) {
    val scope = rememberCoroutineScope()
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
                        badgeContent()
                        Spacer(modifier = Modifier.height(8.dp))
                        ScreenTitle(
                            title = message,
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
                if (mode == Mode.SANTA && pagerState.pageCount == 0) {
                    CalendarCardSantaEmpty(Modifier.align(CenterHorizontally)) { onClickAdd() }
                } else {
                    pagerContent()
                    Spacer(modifier = Modifier.height(30.dp))
                    RemainDateTimeText(
                        modifier = Modifier.align(CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    Indicator(
                        modifier = Modifier.align(CenterHorizontally),
                        mode = mode,
                        calendarCount = pagerState.pageCount,
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarPager(
    pagerState: PagerState,
    cardContent: @Composable (Modifier, Int) -> Unit
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
        cardContent(animatedModifier, it)
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
    mode: Mode,
    calendarCount: Int,
    currentIndex: Int,
    onChangedCurrentIndex: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(calendarCount) {
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