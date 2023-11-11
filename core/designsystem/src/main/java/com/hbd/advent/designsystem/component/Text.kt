package com.hbd.advent.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.theme.AdventTheme

@Composable
fun ScreenTitle(title: String){
    Text(
        text = title,
        style = AdventTheme.typography.H1
    )
}

@Composable
fun ScreenTitleWithSubtitle(
    title: String,
    subtitle: String,
    textAlign: TextAlign? = null,
    horizontalAlign: Alignment.Horizontal = Alignment.Start
){
    Column {
        Text(
            modifier = Modifier.align(horizontalAlign),
            text = title,
            style = AdventTheme.typography.H1,
            textAlign = textAlign
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = subtitle,
            style = AdventTheme.typography.H4,
            color = AdventTheme.colors.Black400,
            textAlign = textAlign
        )
    }
}