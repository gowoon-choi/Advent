package com.hbd.create_calendar.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.create_calendar.R

enum class Theme(val themeNameResId: Int) {
    GREEN(R.string.calendar_theme_name_green),
    RED(R.string.calendar_theme_name_red)
}

@Composable
fun ThemeSelector(
    modifier: Modifier = Modifier,
    selectedTheme: Theme,
    onClickTheme: (Theme) -> Unit,
){
    Row(modifier) {
        ThemeSelectCard(theme = Theme.GREEN, selected = selectedTheme == Theme.GREEN) {
            onClickTheme(Theme.GREEN)
        }
        Spacer(modifier = Modifier.width(25.dp))
        ThemeSelectCard(theme = Theme.RED, selected = selectedTheme == Theme.RED) {
            onClickTheme(Theme.RED)
        }
    }
}

@Composable
fun ThemeSelectCard(
    theme: Theme,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column {
        when (theme) {
            Theme.GREEN -> {
                GreenThemeCard(selected = selected, onClick = onClick)
            }

            Theme.RED -> {
                RedThemeCard(selected = selected, onClick = onClick)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = theme.name,
            style = AdventTheme.typography.H3,
            color = AdventTheme.colors.Black600
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = theme.themeNameResId),
            style = AdventTheme.typography.Caption,
            color = AdventTheme.colors.Black600
        )
    }

}

@Composable
fun GreenThemeCard(selected: Boolean, onClick: () -> Unit) {
    IconButton(
        modifier = Modifier
            .width(135.dp)
            .height(158.dp),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.wrapContentSize(),
            painter =
            if (selected) painterResource(id = R.drawable.green_theme_selected)
            else painterResource(id = R.drawable.green_theme_unselected),
            tint = Color.Unspecified,
            contentDescription = null
        )
    }
}

@Composable
fun RedThemeCard(selected: Boolean, onClick: () -> Unit) {
    IconButton(
        modifier = Modifier
            .width(135.dp)
            .height(158.dp),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            painter =
            if (selected) painterResource(id = R.drawable.red_theme_selected)
            else painterResource(id = R.drawable.red_theme_unselected),
            tint = Color.Unspecified,
            contentDescription = null
        )
    }
}