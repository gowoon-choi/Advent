package com.hbd.advent.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hbd.advent.designsystem.theme.AdventTheme
import com.hbd.advent.designsystem.util.dpToPx

@Composable
fun SingleLineInputBox(
    modifier: Modifier = Modifier,
    text: TextFieldValue,
    hint: String,
    onTextChanged: (TextFieldValue) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .onFocusChanged { isFocused = it.isFocused },
        value = text,
        onValueChange = { onTextChanged(it) },
        textStyle = AdventTheme.typography.Body1,
        singleLine = true,
        decorationBox = { innerTextField ->
            InputTextFieldBox(
                modifier = Modifier.fillMaxSize(),
                color = if(isFocused) AdventTheme.colors.GreenPrimary else AdventTheme.colors.Black200,
                borderSize = 1.dp.dpToPx()
            ) {
                if(text.text.isEmpty()){
                    Text(
                        text = hint,
                        style = AdventTheme.typography.Body1,
                        color = AdventTheme.colors.Black400)
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun InputTextFieldBox(
    modifier: Modifier = Modifier,
    color: Color,
    borderSize: Float,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.drawBehind {
        drawLine(
            color = color,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = borderSize
        )
    }.padding(horizontal = 8.dp)) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            content()
        }
    }
}


@Preview
@Composable
fun InputPreview() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SingleLineInputBox(
            modifier = Modifier.padding(20.dp),
            text = text,
            hint = "사용할 닉네임을 입력해주세요"
        ){ text = it }
    }
}