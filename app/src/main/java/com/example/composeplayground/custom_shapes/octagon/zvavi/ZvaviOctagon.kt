package com.example.composeplayground.custom_shapes.octagon.zvavi

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

private object ZvaviOctagonConstants {
    const val SEGMENTS_COUNT = 8
    const val SEGMENT_ANGLE = 45.0
    const val INITIAL_ANGLE_OFFSET = -22.5
    const val RADIUS_DELIMITER_1 = 1.3f
    const val RADIUS_DELIMITER_2 = 2.3f
    const val RADIUS_DELIMITER_3 = 3.3f
    const val RADIUS_HEIGHT_DIVISOR = 10.5f
    const val COMPASS_RADIUS_DIVISOR = 2.7f
    const val TEXT_SIZE = 32f
    const val NORTH_OFFSET = 5
}

class ZvaviOctagon : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        TODO("Not yet implemented")
    }

    fun createSegments(size: Size, radiusDelimiter: Float): List<Path> = 
        (0 until ZvaviOctagonConstants.SEGMENTS_COUNT).map { i ->
            val (centerX, centerY) = size.width / 2 to size.height / 2
            val radius = radiusDelimiter * size.height / ZvaviOctagonConstants.RADIUS_HEIGHT_DIVISOR
            
            val angle1 = Math.toRadians((i * ZvaviOctagonConstants.SEGMENT_ANGLE) + ZvaviOctagonConstants.INITIAL_ANGLE_OFFSET)
            val angle2 = Math.toRadians(((i + 1) * ZvaviOctagonConstants.SEGMENT_ANGLE) + ZvaviOctagonConstants.INITIAL_ANGLE_OFFSET)
            
            Path().apply {
                moveTo(centerX, centerY)
                lineTo(
                    centerX + radius * cos(angle1).toFloat(),
                    centerY + radius * sin(angle1).toFloat()
                )
                lineTo(
                    centerX + radius * cos(angle2).toFloat(),
                    centerY + radius * sin(angle2).toFloat()
                )
                close()
            }
        }
}

@Preview(showBackground = true)
@Composable
fun AssOctaGonFun(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(150.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(150.dp)) {
            val shape = ZvaviOctagon()
            val segments1 = shape.createSegments(size, radiusDelimiter = ZvaviOctagonConstants.RADIUS_DELIMITER_1)
            val segments2 = shape.createSegments(size, radiusDelimiter = ZvaviOctagonConstants.RADIUS_DELIMITER_2)
            val segments3 = shape.createSegments(size, radiusDelimiter = ZvaviOctagonConstants.RADIUS_DELIMITER_3)
            val centerX = size.width / 2
            val centerY = size.height / 2
            val radius = size.height / ZvaviOctagonConstants.COMPASS_RADIUS_DIVISOR

            segments3.forEachIndexed { index, segment ->
                println(index)
                drawPath(
                    path = segment,
                    color = if (index == 1) Color.Green else Color.Blue,
                )
                drawPath(
                    path = segment,
                    color = Color.Black,
                    style = Stroke(width = 1.dp.toPx())
                )
            }
            segments2.forEachIndexed { index, segment ->
                drawPath(
                    path = segment,
                    color = if ((index+1) % 2 == 0) Color.White else Color.Blue,
                )
                drawPath(
                    path = segment,
                    color = Color.Black,
                    style = Stroke(width = 1.dp.toPx())
                )
            }
            segments1.forEachIndexed { index, segment ->
                drawPath(
                    path = segment,
                    color = if (index % 2 == 0) Color.White else Color.Blue,
                )
                drawPath(
                    path = segment,
                    color = Color.Black,
                    style = Stroke(width = 1.dp.toPx())
                )
            }
            // Draw text outside the outer line
            val labels = listOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
            for (i in labels.indices) {
                val angle = Math.toRadians(i * ZvaviOctagonConstants.SEGMENT_ANGLE - 90) // Start from top (N)
                val textX = centerX + radius * cos(angle).toFloat()
                val textY = centerY + radius * sin(angle).toFloat() +
                        if (labels[i] == "N") ZvaviOctagonConstants.NORTH_OFFSET.dp.toPx() // Adjust for "N"
                        else 0f

                drawContext.canvas.nativeCanvas.drawText(
                    labels[i],
                    textX,
                    textY,
                    Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = ZvaviOctagonConstants.TEXT_SIZE
                        textAlign = Paint.Align.CENTER
                    }
                )
            }
        }
    }
}