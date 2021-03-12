package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun Start(onStart: () -> Unit) {
    Button(
        color = Color.Yellow,
        onClick = onStart
    ) {
        Text(
            text = "Start",
            style = MaterialTheme.typography.button,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Composable
internal fun Pause(onPause: () -> Unit) {
    Button(
        color = Color.White,
        onClick = onPause
    ) {
        Text(
            text = "Pause",
            style = MaterialTheme.typography.button,
            color = Color.Black,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Composable
internal fun Stop(onStop: () -> Unit) {
    Button(
        color = Color.White,
        onClick = onStop
    ) {
        Text(
            text = "Stop",
            style = MaterialTheme.typography.button,
            color = Color.Black,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Composable
internal fun Button(
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val interaction = remember { MutableInteractionSource() }
    val squareShape = RoundedCornerShape(4.dp)
    Box(
        modifier
            .border(width = 0.dp, color = Color.Magenta, shape = squareShape)
            .background(shape = squareShape, color = color)
            .width(100.dp)
            .aspectRatio(1f)
            .clickable(
                interactionSource = interaction,
                indication = null,
                onClick = onClick
            ),
        content = content
    )
}