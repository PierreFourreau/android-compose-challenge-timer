package com.example.androiddevchallenge.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Chrono(
    seconds: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        ChronoSeconds {
            Text(
                text = "${seconds}s",
                style = MaterialTheme.typography.h4,
                color = Color.Blue,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }
    }
}

@Composable
internal fun ChronoSeconds(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(95.dp)
            .shadow(elevation = 1.dp, shape = CircleShape)
            .background(color = Color.White, shape = CircleShape)
            .clip(CircleShape),
        content = content
    )
}