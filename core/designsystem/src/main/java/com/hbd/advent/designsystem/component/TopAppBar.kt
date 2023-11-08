package com.hbd.advent.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.hbd.advent.designsystem.R
import com.hbd.advent.designsystem.theme.AdventTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(
    title: String? = null,
    onClickNav: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            title?.let {
                Text(text = it, style = AdventTheme.typography.H2)
            }
        },
        navigationIcon = {
            IconButton(onClick = onClickNav) {
                Icon(painter = painterResource(id = R.drawable.back), contentDescription = null)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBarWithCloseButton(
    title: String? = null,
    onClickNav: () -> Unit,
    onClickClose: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            title?.let {
                Text(text = it, style = AdventTheme.typography.H2)
            }
        },
        navigationIcon = {
            IconButton(onClick = onClickNav) {
                Icon(painter = painterResource(id = R.drawable.back), contentDescription = null)
            }
        },
        actions = {
            Row {
                IconButton(onClick = onClickClose) {
                    Icon(painter = painterResource(id = R.drawable.close), contentDescription = null)
                }
            }
        }
    )
}

@Composable
fun DefaultAppBarWithTextButton() {

}

@Composable
fun HomeAppBar() {

}

@Composable
fun CalendarAppBar() {

}