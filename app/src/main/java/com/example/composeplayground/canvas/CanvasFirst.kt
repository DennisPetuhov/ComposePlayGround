package com.example.composeplayground.canvas

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun DrawRect(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(100.dp)
    ) {

        drawRect(color = Color.Red, size = size)
    }

}
@Preview
@Composable
fun SquareComponent(componentSize: Dp = 210.dp) {
    val canvasSizePx = with(LocalDensity.current) {
        componentSize.toPx()
    }
    val infiniteScale = rememberInfiniteTransition(label = "")
    val animatedDotScale by infiniteScale.animateFloat(
        initialValue = 20f,
        targetValue = canvasSizePx / 2,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    Canvas(
        modifier = Modifier.size(componentSize)
    ) {
        // draw a square the size of our canvas
        withTransform({rotate(45f)
            translate (top =size.width / 3f )}){}
        drawRect(
//            color = Color.Blue,
            brush = Brush.linearGradient(
                colors = listOf(Color.Green, Color.Magenta)),
            size = size
        )

        // place circle on top of square
        drawCircle(
            color = Color.White,
            center = Offset(x = size.width / 2f, y = size.height / 2f),
//            radius = size.minDimension / 4f
            radius = animatedDotScale
        )
    }
}