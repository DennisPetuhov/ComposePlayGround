package com.example.composeplayground.animation.shimmer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

//https://medium.com/@hzolfagharipour/shimmer-animation-in-jetpack-compose-without-recomposition-04d1317634a7
@Composable
fun Modifier.myShimmer(
    durationMillis: Int = 1000,
): Modifier {
    val transition = rememberInfiniteTransition("")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "",
    )

//    return background(
    return drawBehind {
        this.drawRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.LightGray.copy(alpha = 0.2f),
                    Color.LightGray.copy(alpha = 1.0f),
                    Color.LightGray.copy(alpha = 0.2f),
                ),
                start = Offset(translateAnimation.value, translateAnimation.value),
                end = Offset(translateAnimation.value + 100f, y = translateAnimation.value + 100f),
            )
        )
    }
}
