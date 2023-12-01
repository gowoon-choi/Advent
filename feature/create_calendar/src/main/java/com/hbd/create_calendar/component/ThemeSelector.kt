package com.hbd.create_calendar.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
    val textColor = if(selected) AdventTheme.colors.Black600 else AdventTheme.colors.Black400
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
            color = textColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = theme.themeNameResId),
            style = AdventTheme.typography.Caption,
            color = textColor
        )
    }

}

@Composable
fun GreenThemeCard(selected: Boolean, onClick: () -> Unit) {
    Image(
        modifier = Modifier
            .width(135.dp)
            .height(158.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        painter =
        if (selected) painterResource(id = R.drawable.green_theme_selected)
        else painterResource(id = R.drawable.green_theme_unselected),
        contentDescription = null
    )
}

@Composable
fun RedThemeCard(selected: Boolean, onClick: () -> Unit) {
    Image(
        modifier = Modifier
            .width(135.dp)
            .height(158.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        painter =
        if (selected) painterResource(id = R.drawable.red_theme_selected)
        else painterResource(id = R.drawable.red_theme_unselected),
        contentDescription = null
    )
}

@Preview
@Composable
fun SelectorPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        ThemeSelector(selectedTheme = Theme.RED){

        }
    }
}