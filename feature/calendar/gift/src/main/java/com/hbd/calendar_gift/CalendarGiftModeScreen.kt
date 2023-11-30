package com.hbd.calendar_gift

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.component.CalendarAppBar
import com.hbd.advent.designsystem.component.CalendarGift
import com.hbd.advent.designsystem.component.CalendarTitleWithGiftCount
import com.hbd.advent.designsystem.component.DayBadge
import com.hbd.advent.designsystem.component.ScreenTitle
import com.hbd.advent.designsystem.component.SecondaryButton
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.domain.DomainConst
import com.hbd.domain.model.FeatureMode
import com.hbd.domain.model.GiftGift
import com.hbd.domain.model.GiftGiftState
import com.hbd.domain.model.UiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarGiftModeScreen() {
    val theme by remember { mutableStateOf(UiTheme.GREEN) }
    val title = "캘린더 이름"
    val dday = "24"
    val message = "두근두근\n첫 선물을 열어보아요"
    val gifts = List(DomainConst.giftCount){ GiftGift(GiftGiftState.OPENED) }
    val footerText = "크리스마스엔 모든 선물을 열어볼 수 있어요" // TODO 이거 조건에 따라서 문구 어떻게 되는지 물어보고 넣기

    Scaffold(
        containerColor = AdventTheme.colors.White,
        topBar = {
            CalendarAppBar(
                mode = FeatureMode.SANTA,
                title = title,
                onClickNav = { /*TODO*/ },
                onClickShare = { /*TODO*/ },
                onClickMore = { /*TODO*/ })
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding() + dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
                    bottom = paddingValues.calculateBottomPadding() + dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
                    start = dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
                    end = dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
                )
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column {
                    DayBadge(
                        day = dday,
                        color = if (theme == UiTheme.GREEN) AdventTheme.colors.Green200 else AdventTheme.colors.Red200
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ScreenTitle(title = message)
                }
                SecondaryButton(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    mode = FeatureMode.GIFT,
                    title = stringResource(id = com.hbd.advent.designsystem.R.string.show_all)
                ) {
                    // TODO onclick 모아보기
                }
            }
            Spacer(modifier = Modifier.height(35.dp))
            CalendarTitleWithGiftCount(
                title = stringResource(id = R.string.openable_gift_title),
                giftCount = gifts.size,
                textColor = AdventTheme.colors.Black700,
                countColor = AdventTheme.colors.Black400
            )
            Spacer(modifier = Modifier.height(20.dp))
            CalendarGift(theme = theme, gifts = gifts){
                // TODO onClick Gift
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = footerText,
                style = AdventTheme.typography.Body3,
                color = AdventTheme.colors.Black500,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

    }

}

@Preview
@Composable
fun CalendarGiftPreview() {
    CalendarGiftModeScreen()
}