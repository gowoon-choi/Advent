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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
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
import com.hbd.domain.model.UiTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class Step {
    NAME, THEME
}

@Composable
fun CreateCalendarLandingScreen(
    viewModel: CreateCalendarViewModel,
    navController: NavHostController
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        containerColor = AdventTheme.colors.BgLight,
        topBar = {
            DefaultAppBar {
                navController.navigate(CreateCalendarRoute.homeGraph)
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
                title = stringResource(id = R.string.welcome_title, state.userNickname),
                subtitle = stringResource(id = R.string.welcome_subtitle)
            )
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = com.hbd.advent.designsystem.R.drawable.bg_graphic_rudolf),
                tint = Color.Unspecified,
                contentDescription = null
            )
            Column(
                modifier = Modifier.align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = com.hbd.advent.designsystem.R.drawable.bg_graphic_house),
                    tint = Color.Unspecified,
                    contentDescription = null
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
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
                        title = stringResource(id = com.hbd.advent.designsystem.R.string.create_calendar),
                        enabled = true
                    ) {
                        navController.navigate(CreateCalendarRoute.createCalendar)
                    }
                }
            }
        }
    }
}

@Composable
fun CreateCalendarScreen(
    viewModel: CreateCalendarViewModel,
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    var step by remember { mutableStateOf(Step.NAME) }
    Scaffold(
        containerColor = AdventTheme.colors.BgLight,
        topBar = {
            DefaultAppBarWithCloseButton(
                title = stringResource(id = com.hbd.advent.designsystem.R.string.create_calendar),
                onClickNav = {
                    when (step) {
                        Step.NAME -> navController.navigate(CreateCalendarRoute.homeGraph)
                        Step.THEME -> step = Step.NAME
                    }
                },
                onClickClose = {
                    navController.navigate(CreateCalendarRoute.homeGraph)
                })
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
            Step.NAME -> CalendarNameSettingContent(viewModel, modifier) { step = Step.THEME }
            Step.THEME -> CalendarThemeSettingContent(viewModel, modifier) {
                viewModel.setEvent(CreateCalendarUiEvent.OnClickDone)
            }
        }
    }
    SideEffect {
        coroutineScope.launch {
            viewModel.effect.collect {
                when (it) {
                    is CreateCalendarUiEffect.GoToSuccessScreen -> {
                        navController.navigate(CreateCalendarRoute.createCalendarSuccess)
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarNameSettingContent(
    viewModel: CreateCalendarViewModel,
    modifier: Modifier = Modifier,
    onClickNext: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var text by remember { mutableStateOf(TextFieldValue(state.calendarName)) }
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
                viewModel.setEvent(CreateCalendarUiEvent.UpdateCalendarName(it.text))
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Icon(
                painter = painterResource(id = com.hbd.advent.designsystem.R.drawable.bg_graphic_house),
                tint = Color.Unspecified,
                contentDescription = null
            )
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = com.hbd.advent.designsystem.R.string.common_button_next),
                enabled = text.text.isNotEmpty(),
                onClick = onClickNext
            )
        }
    }
}

@Composable
fun CalendarThemeSettingContent(
    viewModel: CreateCalendarViewModel,
    modifier: Modifier = Modifier,
    onClickDone: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    Box(modifier = modifier) {
        Column {
            ScreenTitleWithSubtitle(
                title = stringResource(id = R.string.calendar_theme_title),
                subtitle = stringResource(id = R.string.calendar_theme_subtitle)
            )
        }
        ThemeSelector(
            modifier = Modifier.align(Alignment.Center),
            selectedTheme = when (state.calendarTheme) {
                UiTheme.GREEN -> Theme.GREEN
                UiTheme.RED -> Theme.RED
            }
        ) {
            viewModel.setEvent(
                CreateCalendarUiEvent.SelectCalendarTheme(
                    when (it) {
                        Theme.GREEN -> UiTheme.GREEN
                        Theme.RED -> UiTheme.RED
                    }
                )
            )
        }
        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            title = stringResource(id = com.hbd.advent.designsystem.R.string.create_calendar),
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
        Column(
            Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(id = com.hbd.advent.designsystem.R.drawable.bg_graphic_rudolf),
                tint = Color.Unspecified,
                contentDescription = null)
            Spacer(modifier = Modifier.height(30.dp))
            ScreenTitleWithSubtitle(
                title = stringResource(id = R.string.create_calendar_success_title),
                subtitle = stringResource(id = R.string.create_calendar_success_subtitle),
                textAlign = TextAlign.Center,
                horizontalAlign = Alignment.CenterHorizontally
            )
        }
        Icon(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(id = com.hbd.advent.designsystem.R.drawable.bg_graphic_house),
            tint = Color.Unspecified,
            contentDescription = null)
    }
    LaunchedEffect(Unit) {
        delay(DELAY_TIME_FOR_SUCCESS_PAGE)
        navController.navigate(CreateCalendarRoute.homeGraph)
    }
}

const val DELAY_TIME_FOR_SUCCESS_PAGE = 1500L
