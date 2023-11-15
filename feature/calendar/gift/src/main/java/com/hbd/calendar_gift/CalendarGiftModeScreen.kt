package com.hbd.calendar_gift

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.R
import com.hbd.advent.designsystem.component.CalendarAppBar
import com.hbd.advent.designsystem.component.DayBadge
import com.hbd.advent.designsystem.component.ScreenTitle
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.domain.model.FeatureMode
import com.hbd.domain.model.UiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarGiftModeScreen() {
    val theme by remember { mutableStateOf(UiTheme.GREEN) }
    val title = "캘린더 이름"
    val dday = "24"
    val message = "두근두근\n첫 선물을 열어보아요"

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
                    top = paddingValues.calculateTopPadding() + dimensionResource(id = R.dimen.default_padding),
                    bottom = paddingValues.calculateBottomPadding() + dimensionResource(id = R.dimen.default_padding),
                    start = dimensionResource(id = R.dimen.default_padding),
                    end = dimensionResource(id = R.dimen.default_padding),
                )
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

            }

        }

    }

}

@Preview
@Composable
fun CalendarGiftPreview() {
    CalendarGiftModeScreen()
}