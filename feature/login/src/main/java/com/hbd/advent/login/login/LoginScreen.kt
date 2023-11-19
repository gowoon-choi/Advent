package com.hbd.advent.login.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.hbd.advent.designsystem.component.ScreenTitle
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.login.R
import com.hbd.advent.login.navigation.LoginNavRoute
import com.hbd.advent.designsystem.R as commonR

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
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
        when(state.state){
            is LoginState.BEFORE -> {
                ScreenTitle(
                    title = stringResource(
                        id = R.string.login_title,
                        stringResource(id = R.string.app_name)
                    )
                )
                Icon(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 100.dp)
                        .clickable {
                            viewModel.setEvent(LoginUiEvent.OnClickLoginButton)
                        },
                    painter = painterResource(id = R.drawable.kakao_login_btn),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            is LoginState.SUCCESS -> {
                if((state.state as LoginState.SUCCESS).newUser){
                    navController.navigate(LoginNavRoute.initNickname)
                } else {
                    navController.navigate(LoginNavRoute.homeGraph)
                }
            }
            is LoginState.FAILED -> {
                // TODO error handling
            }
        }
    }

}