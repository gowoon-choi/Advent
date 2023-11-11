package com.hbd.advent.home.component

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.runtime.LaunchedEffect
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
import com.hbd.advent.home.Mode
import com.hbd.advent.home.R
import kotlinx.coroutines.delay

@Composable
fun Switch(
    modifier: Modifier = Modifier,
    selectedMode: Mode,
    onChangeMode: (Mode) -> Unit
) {
    var state by remember { mutableStateOf(if(selectedMode == Mode.SANTA) 1f else -1f) }
    val animatedAlignment by animateFloatAsState(state, label = "")
    var modeForBg by remember { mutableStateOf(selectedMode) }
    LaunchedEffect(state){
        if(state == 1f){
            modeForBg = Mode.SANTA
            delay(150L)
            onChangeMode(Mode.SANTA)
        }
        if(state == -1f){
            modeForBg = Mode.GIFT
            delay(150L)
            onChangeMode(Mode.GIFT)
        }
    }
    Box(
        modifier = modifier
            .width(60.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(if (modeForBg == Mode.GIFT) AdventTheme.colors.Black200 else AdventTheme.colors.Black650)
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
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(AdventTheme.colors.White)
                    .align(BiasAlignment.Horizontal(animatedAlignment))
                    .clickable { state *= -1 }
            )
        }
    }
}
