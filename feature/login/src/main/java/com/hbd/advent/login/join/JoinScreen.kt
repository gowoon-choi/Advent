package com.hbd.advent.login.join

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hbd.advent.designsystem.component.DefaultButton
import com.hbd.advent.designsystem.component.ScreenTitle
import com.hbd.advent.login.R
import com.hbd.advent.designsystem.R as commonR
import com.hbd.advent.designsystem.component.SingleLineInputBox
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.login.navigation.LoginNavRoute
import kotlinx.coroutines.launch

@Composable
fun InitNicknameScreen(
    viewModel: JoinViewModel = hiltViewModel(),
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val text by remember { mutableStateOf(TextFieldValue(state.nickname)) }
    Box(
        modifier = Modifier
            .background(AdventTheme.colors.BgLight)
            .fillMaxSize()
            .padding(
                top = dimensionResource(id = commonR.dimen.large_padding),
                start = dimensionResource(id = commonR.dimen.default_padding),
                bottom = dimensionResource(id = commonR.dimen.default_padding),
                end = dimensionResource(id = commonR.dimen.default_padding)
            )
    ) {
        Column {
            ScreenTitle(title = stringResource(id = R.string.nickname_title))
            SingleLineInputBox(
                modifier = Modifier.padding(top = 28.dp),
                text = text,
                hint = stringResource(id = R.string.nickname_input_placeholder),
                onTextChanged = { viewModel.setEvent(JoinUiEvent.UpdateNickname(it.text)) })
        }
        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            title = stringResource(id = commonR.string.common_button_next),
            enabled = text.text.isNotEmpty()
        ) {
            viewModel.setEvent(JoinUiEvent.OnClickDone)
        }
    }
    SideEffect {
        scope.launch {
            viewModel.effect.collect{
                when(it){
                    is JoinUiEffect.GoToCreateCalendarScreen -> {
                        navController.navigate(LoginNavRoute.createCalendarGraph)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun InitNicknameScreenPreview() {
    InitNicknameScreen(navController = rememberNavController())
}