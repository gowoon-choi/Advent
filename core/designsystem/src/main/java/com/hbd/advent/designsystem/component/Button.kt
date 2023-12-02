package com.hbd.advent.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.theme.AdventTheme

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    BaseButton(
        modifier = modifier,
        title = title,
        containerColor = AdventTheme.colors.GreenPrimary,
        contentColor = AdventTheme.colors.White,
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun AlertButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    BaseButton(
        modifier = modifier,
        title = title,
        containerColor = AdventTheme.colors.RedPrimary,
        contentColor = AdventTheme.colors.White,
        enabled = true,
        onClick = onClick
    )
}

@Composable
fun NegativeButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    BaseButton(
        modifier = modifier,
        title = title,
        containerColor = AdventTheme.colors.Black200,
        contentColor = AdventTheme.colors.Black500,
        enabled = true,
        onClick = onClick
    )
}

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.run {
            buttonColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = AdventTheme.colors.Black200,
                disabledContentColor = AdventTheme.colors.Black500
            )
        },
        enabled = enabled,
        onClick = onClick
    ) {
        Text(text = title, style = AdventTheme.typography.H2)
    }

}

@Composable
fun CalendarCardButton(
    containerColor: Color,
    contentColor: Color,
    btnTitle: String,
    onClick: () -> Unit
){
    Box(
        Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(8.dp))
            .background(containerColor)
            .padding(vertical = 10.dp, horizontal = 12.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = btnTitle,
            style = AdventTheme.typography.H3,
            color = contentColor
        )
    }
}