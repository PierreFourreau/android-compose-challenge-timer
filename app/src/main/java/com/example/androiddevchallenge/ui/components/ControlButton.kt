package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.darkestGrey
import com.example.androiddevchallenge.ui.theme.green

@Composable
internal fun Start(onStart: () -> Unit) {
    ControlButton(
        width = 100.dp,
        color = Color.White,
        onClick = onStart
    ) {
        Icon(
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = "Start",
            tint = darkestGrey,
            modifier = Modifier
                .size(50.dp)
                .align(alignment = Alignment.Center)
        )
    }
}

@Composable
internal fun Pause(onPause: () -> Unit) {
    ControlButton(
        width = 70.dp,
        color = Color.White,
        onClick = onPause
    ) {
        Icon(
            imageVector = Icons.Rounded.Pause,
            contentDescription = "Pause",
            tint = darkestGrey,
            modifier = Modifier
                .size(50.dp)
                .align(alignment = Alignment.Center)
        )
    }
}

@Composable
internal fun Stop(onStop: () -> Unit) {
    ControlButton(
        width = 70.dp,
        color = Color.White,
        onClick = onStop
    ) {
        Icon(
            imageVector = Icons.Rounded.Stop,
            contentDescription = "Stop",
            tint = darkestGrey,
            modifier = Modifier
                .size(50.dp)
                .align(alignment = Alignment.Center)
        )
    }
}

@Composable
internal fun ControlButton(
    color: Color,
    width: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val interaction = remember { MutableInteractionSource() }
    val isPressed by interaction.collectIsPressedAsState()
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 3.dp else 8.dp,
        animationSpec = tween(400)
    )
    val colorAnimated by animateColorAsState(targetValue = if (isPressed) green else color)

    Box(
        modifier
            .shadow(elevation = elevation, shape = CircleShape)
            .background(color = colorAnimated, shape = CircleShape)
            .border(width = 2.dp, color = darkestGrey, shape = CircleShape)
            .clip(CircleShape)
            .width(width)
            .aspectRatio(1f)
            .clickable(
                interactionSource = interaction,
                indication = null,
                onClick = onClick
            ),
        content = content
    )
}