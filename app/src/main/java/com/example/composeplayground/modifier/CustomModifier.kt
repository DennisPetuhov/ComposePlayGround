package com.example.composeplayground.modifier

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.unit.IntSize
//composed  is outdated  - no performance
@Composable
fun Modifier.shimmerEffect() = composed {
    val size = remember { mutableStateOf(IntSize(0, 0)) }
    val transition = rememberInfiniteTransition()
    val startOffsetX = transition.animateFloat(
        initialValue = -2 * size.value.width.toFloat(),
        targetValue = 2 * size.value.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )
    this.background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX.value, 0f),
            end = Offset(startOffsetX.value + size.value.width.toFloat(), size.value.height.toFloat())
        )
    ).onGloballyPositioned {
        size.value = it.size
    }
}

@Composable
fun Modifier.pulsatingScale(
    pulseFraction: Float = 1.2f,
    duration: Int = 1000
): Modifier {
    val infiniteTransition = rememberInfiniteTransition()
    val scale = infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(duration),
            repeatMode = RepeatMode.Reverse
        )
    )

    return this.scale(scale.value)
}
//1. Extractability: CMF is limited to use within the Composition scope, while composed() can be extracted and used more flexibly.
//2. CompositionLocal resolution: CMF resolves CompositionLocal values at the call site, while composed() resolves them at the usage site.
//3. State resolution: CMF resolves state only once at the call site, while composed() resolves state at the usage site for each Layout.
//4. Performance: CMF performs better than composed() due to avoiding the expensive materialize() call.


// Modifier factory
fun Modifier.circle(color: Color) = this then CircleElement(color)

// ModifierNodeElement
private data class CircleElement(val color: Color) : ModifierNodeElement<CircleNode>() {
    override fun create() = CircleNode(color)

    override fun update(node: CircleNode) {
        node.color = color
    }
}

// Modifier.Node
private class CircleNode(var color: Color) : DrawModifierNode, Modifier.Node() {
    override fun ContentDrawScope.draw() {
        drawCircle(color)
    }
}



//ModifierNodeElement classes are stateless and new instances are allocated each recomposition,each
//whereas Modifier.Node classes can be stateful and will survive across multiple recompositions, and can even be reused.

//The first step is to create a class which implements the Modifier.Node along with DrawModifierNode.
//There are multiple factory nodes which compose provides out of the box. Here we want to draw something
//hence we are using the DrawModifierNode. If we wanted to do something with user inputs or gestures we might want to use PointerInputModifierNode