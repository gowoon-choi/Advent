package com.hbd.advent.login.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.login.R
import com.hbd.advent.login.navigation.LoginNavRoute
import com.hbd.advent.designsystem.R as commonR

@Composable
fun LoginScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .background(AdventTheme.colors.BgLight)
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(
                top = dimensionResource(id = commonR.dimen.large_padding),
                start = dimensionResource(id = commonR.dimen.default_padding)
            ),
            text = stringResource(
                id = R.string.login_title,
                stringResource(id = R.string.app_name)
            ),
            style = AdventTheme.typography.H1
        )
        Icon(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
                .clickable {
                    navController.navigate(LoginNavRoute.initNickname) },
            painter = painterResource(id = R.drawable.kakao_login_btn),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }

}