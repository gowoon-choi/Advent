package com.hbd.advent.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.hbd.advent.designsystem.R
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.designsystem.theme.Colors
import com.hbd.domain.model.FeatureMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(
    title: String? = null,
    onClickNav: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
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
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
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
                    Icon(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    mode: FeatureMode,
    onClickAdd: () -> Unit,
    onClickProfile: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
        title = { },
        navigationIcon = {
            // TODO - logo
        },
        actions = {
            Row {
                IconButton(onClick = onClickAdd) {
                    Icon(
                        painter = painterResource(id = R.drawable.new_calendar),
                        contentDescription = null,
                        tint = if(mode == FeatureMode.GIFT) AdventTheme.colors.Black300 else AdventTheme.colors.Black600
                    )
                }
                IconButton(onClick = onClickProfile) {
                    Icon(
                        painter = painterResource(id = R.drawable.my_filled),
                        contentDescription = null,
                        tint = if(mode == FeatureMode.GIFT) AdventTheme.colors.Black300 else AdventTheme.colors.Black600
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarAppBar(
    mode: FeatureMode,
    title: String,
    onClickNav: () -> Unit,
    onClickShare: () -> Unit,
    onClickMore: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            IconButton(onClick = onClickNav) {
                Icon(painter = painterResource(id = R.drawable.back), contentDescription = null)
            }
        },
        title = {
            Text(text = title, style = AdventTheme.typography.H2)
        },
        actions = {
            Row {
                IconButton(onClick = onClickShare) {
                    Icon(
                        painter = painterResource(id = R.drawable.share_filled),
                        contentDescription = null,
                        tint = if(mode == FeatureMode.SANTA) AdventTheme.colors.Black450 else AdventTheme.colors.Black300
                    )
                }
                IconButton(onClick = onClickMore) {
                    Icon(
                        painter = painterResource(id = R.drawable.more),
                        contentDescription = null,
                        tint = if(mode == FeatureMode.SANTA) AdventTheme.colors.Black450 else AdventTheme.colors.Black300
                    )
                }
            }
        }
    )
}

@Composable
fun DefaultAppBarWithTextButton() {

}