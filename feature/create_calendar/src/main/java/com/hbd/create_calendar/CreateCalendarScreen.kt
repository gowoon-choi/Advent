package com.hbd.create_calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hbd.advent.designsystem.component.DefaultAppBar
import com.hbd.advent.designsystem.component.DefaultAppBarWithCloseButton
import com.hbd.advent.designsystem.component.DefaultButton
import com.hbd.advent.designsystem.component.NegativeButton
import com.hbd.advent.designsystem.component.ScreenTitleWithSubtitle
import com.hbd.advent.designsystem.component.SingleLineInputBox
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.create_calendar.component.Theme
import com.hbd.create_calendar.component.ThemeSelector
import com.hbd.create_calendar.navigation.CreateCalendarRoute
import com.hbd.domain.DomainConst
import kotlinx.coroutines.delay

enum class Step {
    NAME, THEME
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCalendarLandingScreen(navController: NavHostController) {
    // TODO - 이전 페이지에서 받을듯..?
    val nickname = "고운"
    Scaffold(
        containerColor = AdventTheme.colors.BgLight,
        topBar = {
            DefaultAppBar {
                // TODO go to home ( back button in appbar )
            }
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding() + dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
                    bottom = it.calculateBottomPadding() + dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
                    start = dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
                    end = dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding)
                )
        ) {
            ScreenTitleWithSubtitle(
                title = stringResource(id = R.string.welcome_title, nickname),
                subtitle = stringResource(id = R.string.welcome_subtitle)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter)
            ) {
                NegativeButton(
                    modifier = Modifier.weight(3f),
                    title = stringResource(id = R.string.skip_button_title)
                ) {
                    navController.navigate(CreateCalendarRoute.homeGraph)
                }
                Spacer(modifier = Modifier.width(16.dp))
                DefaultButton(
                    modifier = Modifier.weight(5f),
                    title = stringResource(id = R.string.create_calendar_button_title),
                    enabled = true
                ) {
                    navController.navigate(CreateCalendarRoute.createCalendar)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCalendarScreen(navController: NavHostController) {
    // TODO
    var step by remember { mutableStateOf(Step.THEME) }
    Scaffold(
        containerColor = AdventTheme.colors.BgLight,
        topBar = {
            DefaultAppBarWithCloseButton(
                title = stringResource(id = R.string.create_calendar_appbar_title),
                onClickNav = { /*TODO*/ },
                onClickClose = {})
        }
    ) { paddingValues ->
        val modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding() + dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
                bottom = paddingValues.calculateBottomPadding() + dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
                start = dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
                end = dimensionResource(id = com.hbd.advent.designsystem.R.dimen.default_padding),
            )
        when (step) {
            Step.NAME -> CalendarNameSettingContent(modifier) { step = Step.THEME }
            Step.THEME -> CalendarThemeSettingContent(modifier) {
                navController.navigate(
                    CreateCalendarRoute.createCalendarSuccess
                )
            }
        }
    }
}

@Composable
fun CalendarNameSettingContent(
    modifier: Modifier = Modifier,
    onClickNext: () -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Box(modifier = modifier) {
        Column {
            ScreenTitleWithSubtitle(
                title = stringResource(id = R.string.calendar_name_title),
                subtitle = stringResource(
                    id = R.string.calendar_name_subtitle,
                    DomainConst.creatableCalendarCount
                )
            )
            Spacer(modifier = Modifier.height(18.dp))
            SingleLineInputBox(
                text = text,
                hint = stringResource(id = R.string.calendar_name_input_placeholder)
            ) {
                text = it
            }
        }
        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            title = stringResource(id = com.hbd.advent.designsystem.R.string.common_button_next),
            enabled = text.text.isNotEmpty(),
            onClick = onClickNext
        )
    }
}

@Composable
fun CalendarThemeSettingContent(
    modifier: Modifier = Modifier,
    onClickDone: () -> Unit
) {
    var currentTheme by remember { mutableStateOf(Theme.GREEN) }
    Box(modifier = modifier) {
        Column {
            ScreenTitleWithSubtitle(
                title = stringResource(id = R.string.calendar_theme_title),
                subtitle = stringResource(id = R.string.calendar_theme_subtitle)
            )
        }
        ThemeSelector(
            modifier = Modifier.align(Alignment.Center),
            selectedTheme = currentTheme
        ) { currentTheme = it }
        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            title = stringResource(id = R.string.create_calendar_appbar_title),
            enabled = true,
            onClick = onClickDone
        )
    }
}

@Composable
fun CreateCalendarSuccessScreen(navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .background(AdventTheme.colors.BgLight)
    ) {
        // TODO Column for graphic area
        Column(
            Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        ) {
            ScreenTitleWithSubtitle(
                title = stringResource(id = R.string.create_calendar_success_title),
                subtitle = stringResource(id = R.string.create_calendar_success_subtitle),
                textAlign = TextAlign.Center,
                horizontalAlign = Alignment.CenterHorizontally
            )
        }
    }
    LaunchedEffect(Unit) {
        delay(DELAY_TIME_FOR_SUCCESS_PAGE)
        navController.navigate(CreateCalendarRoute.homeGraph)
    }
}

const val DELAY_TIME_FOR_SUCCESS_PAGE = 1500L
