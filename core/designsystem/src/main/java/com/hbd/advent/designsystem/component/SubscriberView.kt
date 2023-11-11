package com.hbd.advent.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.R
import com.hbd.advent.designsystem.theme.AdventTheme

@Composable
fun SubscriberView(
    contentColor: Color, subscriberCount: Int
) {
    Row {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.my_filled),
            contentDescription = null,
            tint = contentColor
        )
        Text(
            text = stringResource(id = R.string.subscriber) + " " + subscriberCount + stringResource(id = R.string.subscriber_unit),
            style = AdventTheme.typography.Body3,
            color = contentColor
        )
    }
}