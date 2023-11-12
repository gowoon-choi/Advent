package com.hbd.advent.home.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.home.R
import com.hbd.domain.model.FeatureMode
import kotlinx.coroutines.delay

@Composable
fun Switch(
    modifier: Modifier = Modifier,
    selectedMode: FeatureMode,
    onChangeMode: (FeatureMode) -> Unit
) {
    var state by remember { mutableStateOf(if(selectedMode == FeatureMode.SANTA) 1f else -1f) }
    val animatedColor by animateColorAsState(
        if(state == -1f) AdventTheme.colors.Black200 else AdventTheme.colors.Black650,
        tween(500),
        label = ""
    )
    val animatedAlignment by animateFloatAsState(
        state,
        tween(500),
        label = ""
    ){
        if(state == 1f){
            onChangeMode(FeatureMode.SANTA)
        }
        if(state == -1f){
            onChangeMode(FeatureMode.GIFT)
        }
    }
    Box(
        modifier = modifier
            .width(60.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(animatedColor)
            .padding(5.dp)
    ){
        Icon(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 2.dp),
            painter = painterResource(id = R.drawable.switch_track_gift),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 2.dp),
            painter = painterResource(id = R.drawable.switch_track_santa),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Column(Modifier.fillMaxSize()){
            Box(
                modifier = Modifier
                    .size(23.dp)
                    .clip(CircleShape)
                    .background(AdventTheme.colors.White)
                    .align(BiasAlignment.Horizontal(animatedAlignment))
                    .clickable { state *= -1 }
            )
        }
    }
}
