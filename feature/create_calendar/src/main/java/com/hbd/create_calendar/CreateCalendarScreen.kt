package com.hbd.create_calendar

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hbd.advent.designsystem.component.DefaultAppBar
import com.hbd.advent.designsystem.component.DefaultAppBarWithCloseButton
import com.hbd.advent.designsystem.component.DefaultButton
import com.hbd.advent.designsystem.component.NegativeButton
import com.hbd.advent.designsystem.component.SingleLineInputBox
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.domain.DomainConst

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
            Column {
                Text(
                    text = stringResource(id = R.string.welcome_title, nickname),
                    style = AdventTheme.typography.H1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.welcome_subtitle),
                    style = AdventTheme.typography.H4,
                    color = AdventTheme.colors.Black400
                )
            }
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
                    // TODO onClick - go to home
                }
                Spacer(modifier = Modifier.width(16.dp))
                DefaultButton(
                    modifier = Modifier.weight(5f),
                    title = stringResource(id = R.string.create_calendar_button_title),
                    enabled = true
                ) {
                    // TODO onClick - go to next screen
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCalendarScreen() {
    var step by remember { mutableStateOf(Step.NAME) }
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
            Step.NAME -> CalendarNameSettingContent(modifier)
            Step.THEME -> CalendarThemeSettingContent(modifier)
        }
    }
}

@Composable
fun CalendarNameSettingContent(
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Box(modifier = modifier) {
        Column {
            Text(
                text = stringResource(id = R.string.calendar_name_title),
                style = AdventTheme.typography.H1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.calendar_name_subtitle, DomainConst.creatableCalendarCount),
                style = AdventTheme.typography.H4,
                color = AdventTheme.colors.Black400
            )
            Spacer(modifier = Modifier.height(18.dp))
            SingleLineInputBox(
                text = text,
                hint = stringResource(id = R.string.calendar_name_input_placeholder)){
                text = it
            }
        }
        DefaultButton(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            title = stringResource(id = com.hbd.advent.designsystem.R.string.common_button_next),
            enabled = text.text.isNotEmpty()) {
            // TODO onClickButton
        }
    }
}

@Composable
fun CalendarThemeSettingContent(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column {
            Text(
                text = stringResource(id = R.string.calendar_theme_title),
                style = AdventTheme.typography.H1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.calendar_theme_subtitle),
                style = AdventTheme.typography.H4,
                color = AdventTheme.colors.Black400
            )
        }
    }
}

@Composable
fun CreateCalendarDoneScreen() {

}

@Preview
@Composable
fun CreateCalendarScreenPreview() {
    CreateCalendarScreen()
}
