package com.example.composeplayground.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import kotlinx.coroutines.launch

private class ShimmerNode(
    var shimmerColors: List<Color> = listOf(
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 1.0f),
        Color.LightGray.copy(alpha = 0.2f),
    ),
    var animationValue: Float
) : Modifier.Node(), DrawModifierNode {
    private val alpha = Animatable(1f)

    override fun ContentDrawScope.draw() {
        val brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(animationValue - 100f, animationValue - 100f),
            end = Offset(animationValue + 100f, animationValue + 100f),
        )
        drawRect(brush)
    }

    override fun onAttach() {
        coroutineScope.launch {
            alpha.animateTo(
                0f, animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        easing = LinearEasing,
                    ),
                    repeatMode = RepeatMode.Restart,
                )
            )
        }
    }
}

private class ShimmerElement(
    private val shimmerColors: List<Color>,
    private val animationValue: Float
) : ModifierNodeElement<ShimmerNode>() {

    override fun create(): ShimmerNode {
        return ShimmerNode(shimmerColors, animationValue)
    }

    override fun update(node: ShimmerNode) {
        node.shimmerColors = shimmerColors
        node.animationValue = animationValue
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShimmerElement) return false
        return shimmerColors == other.shimmerColors &&
                animationValue == other.animationValue
    }

    override fun hashCode(): Int {
        var result = shimmerColors.hashCode()
        result = 31 * result + animationValue.hashCode()
        return result
    }
//
//    override fun InspectorInfo.inspectableProperties() {
//        name = "shimmer"
//        properties["shimmerColors"] = shimmerColors
//        properties["animationValue"] = animationValue
//    }
}

@Composable
fun Modifier.shimmerEffect(
    shimmerColors: List<Color> = listOf(
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
    ),
    animationValue: Float
)
//    this then ShimmerElement(shimmerColors, animationValue)
        : Modifier {
    val transition = rememberInfiniteTransition(label = "shimmer_transition")
    // Fix: Get the value directly from the State<Float>
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmer_animation",
    ).value

    return this.then(ShimmerElement(shimmerColors, translateAnimation))
}