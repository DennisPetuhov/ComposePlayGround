package com.example.composeplayground.custom_shapes.octagon

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
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

class AssOctagon : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        TODO("Not yet implemented")
    }

    fun createSegment(size: Size): Path {
        val path = Path().apply {
            val width = size.width
            val height = size.height

            val angle = 45.0
            val radians = Math.toRadians(angle / 2)
            val radius = height / 3
            val cPointLength = height / 2.2f
            val centerX = width / 2
            val centerY = height / 2
            val aX = centerX - radius * cos(Math.toRadians(67.5)).toFloat()
            val aY = centerX - radius * sin(Math.toRadians(67.5)).toFloat()
            val bX = centerX + radius * cos(Math.toRadians(67.5)).toFloat()
            val bY = centerX - radius * sin(Math.toRadians(67.5)).toFloat()
            val cX = centerX
            val cY = centerX - cPointLength * sin(Math.toRadians(90.0)).toFloat()

            moveTo(centerX, centerY)
//            lineTo(aX, aY)
            lineTo(cX, cY)
            lineTo(bX, bY)
            close()
        }
        return path
    }

    fun createSegments(size: Size): List<Path> {
        val segments = mutableListOf<Path>()
        val width = size.width
        val height = size.height
        val centerX = width / 2
        val centerY = height / 2
        val radius = height / 3

        for (i in 0 until 8) {
            val angle1 = Math.toRadians((i * 45.0) - 22.5)
            val angle2 = Math.toRadians(((i + 1) * 45.0) - 22.5)

            val aX = centerX + radius * cos(angle1).toFloat()
            val aY = centerY + radius * sin(angle1).toFloat()
            val bX = centerX + radius * cos(angle2).toFloat()
            val bY = centerY + radius * sin(angle2).toFloat()

            val cX = centerX + 3 * radius * cos(angle1 - 22.5).toFloat()
            val cY = centerY + 3 * radius * sin(angle1 - 22.5).toFloat()

            val path = Path().apply {
                moveTo(centerX, centerY)
                lineTo(aX, aY)
                lineTo(cX, cY)
                lineTo(bX, bY)
                close()
            }
            segments.add(path)
        }
        return segments
    }

    fun createSegmentsBeta(size: Size, radiusDelimiter: Float): List<Path> {
        val segments = mutableListOf<Path>()
        val width = size.width
        val height = size.height
        val centerX = width / 2
        val centerY = height / 2
        val radius = radiusDelimiter * height / 10.5f
        val cPointLength = radius * 1.3636363f
        for (i in 0 until 8) {
            val angle1 = Math.toRadians((i * 45.0) - 22.5)
            val angle2 = Math.toRadians(((i + 1) * 45.0) - 22.5)

            val aX = centerX + radius * cos(angle1).toFloat()
            val aY = centerY + radius * sin(angle1).toFloat()
            val bX = centerX + radius * cos(angle2).toFloat()
            val bY = centerY + radius * sin(angle2).toFloat()

            val cX = centerX + cPointLength * cos(Math.toRadians(i * 45.0)).toFloat()
            val cY = centerY + cPointLength * sin(Math.toRadians(i * 45.0)).toFloat()

            val path = Path().apply {
                moveTo(centerX, centerY)
                lineTo(aX, aY)
//                lineTo(cX, cY)
                lineTo(bX, bY)
                close()
            }
            segments.add(path)
        }
        return segments
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
            val shape = AssOctagon()
            val segment = shape.createSegment(size)
            val segments1 = shape.createSegmentsBeta(size, radiusDelimiter = 1.3f)
            val segments2 = shape.createSegmentsBeta(size, radiusDelimiter = 2.3f)
            val segments3 = shape.createSegmentsBeta(size, radiusDelimiter = 3.3f)
            val centerX = size.width / 2
            val centerY = size.height / 2
            val radius = size.height / 2.7f



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
                val angle = Math.toRadians(i * 45.0 - 90) // Start from top (N)
                val textX = centerX + radius * cos(angle).toFloat()
                val textY = centerY + radius * sin(angle).toFloat() +
                        if (labels[i] == "N") +5.dp.toPx() // Adjust for "N"
//                        else if (labels[i] == "S") -0.dp.toPx() // Adjust for "S"
                        else 0f

                drawContext.canvas.nativeCanvas.drawText(
                    labels[i],
                    textX,
                    textY,
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 32f
                        textAlign = android.graphics.Paint.Align.CENTER
                    }
                )
            }
        }
    }

}