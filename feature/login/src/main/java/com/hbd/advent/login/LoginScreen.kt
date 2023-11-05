package com.hbd.advent.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.theme.AdventTheme

@Composable
internal fun LoginScreen() {
    AdventTheme {
        Box(
            modifier = Modifier
                .background(AdventTheme.colors.BgLight)
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(top = 60.dp, start = 20.dp),
                text = stringResource(
                    id = R.string.login_title,
                    stringResource(id = R.string.app_name)
                ),
                style = AdventTheme.typography.H1
            )
            Icon(
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 100.dp),
                painter = painterResource(id = R.drawable.kakao_login_btn),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    AdventTheme {
        LoginScreen()
    }
}