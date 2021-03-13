package com.example.androiddevchallenge.ui.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.MainViewModel.Companion.SECONDS_INIT_VALUE
import com.example.androiddevchallenge.ui.theme.green
import com.example.androiddevchallenge.ui.theme.greenDark
import com.example.androiddevchallenge.ui.theme.orange
import com.example.androiddevchallenge.ui.theme.red
import com.example.androiddevchallenge.ui.theme.yellow

@Composable
fun Chrono(
    seconds: Int,
    modifier: Modifier = Modifier
) {
    val progressAngle by animateFloatAsState(
        targetValue = 360f / SECONDS_INIT_VALUE * seconds,
        animationSpec = tween(300)
    )

    Box(
        modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        CircleShape(backgroundColor = if (seconds > 0) Color.Gray else Color.DarkGray) {
            Text(
                text = seconds.toString(),
                style = TextStyle(
                    fontFamily = FontFamily(Font(resId = R.font.custom_font))
                ),
                color = greenDark,
                fontSize = 90.sp,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }
        CircleProgress(angle = progressAngle)
    }
}

@Composable
internal fun CircleShape(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(60.dp)
            .shadow(elevation = 22.dp, shape = CircleShape)
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .clip(CircleShape),
        content = content
    )
}

@Composable
internal fun CircleProgress(
    angle: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(45.dp)
            .drawBehind {
                drawArc(
                    brush = Brush.horizontalGradient(listOf(green, red, yellow, orange)),
                    startAngle = -90f,
                    sweepAngle = angle,
                    useCenter = false,
                    style = Stroke(width = 35f, cap = StrokeCap.Round)
                )
            }
    )
}
