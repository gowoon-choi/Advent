package com.hbd.advent.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.R
import com.hbd.advent.designsystem.theme.AdventTheme

@Composable
fun DayBadge(
    day: String,
    color: Color
){
    Box(modifier = Modifier
        .wrapContentSize()
        .clip(RoundedCornerShape(6.dp))
        .background(color.copy(alpha = 0.13f))
    ){
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            text = stringResource(id = R.string.dday_prefix) + day,
            style = AdventTheme.typography.H3,
            color = color
        )
    }
}

@Preview
@Composable
fun CompoenetPreview(){
    Box(modifier = Modifier.fillMaxSize().background(Color.White)){
        DayBadge(day = "24", color = AdventTheme.colors.Red200)
    }
}