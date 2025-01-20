package com.example.composeplayground.custom_shapes.octagon

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
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
            lineTo(aX, aY)
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
//                lineTo(cX, cY)
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
                lineTo(cX, cY)
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
            val segments1 = shape.createSegmentsBeta(size, radiusDelimiter = 1f)
            val segments2 = shape.createSegmentsBeta(size, radiusDelimiter = 2f)
            val segments3 = shape.createSegmentsBeta(size, radiusDelimiter = 3f)




            segments3.forEachIndexed { index, segment ->
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
//                    color = if (index % 2 == 0) Color.White else Color.Blue,
                    color =Color.Blue,
                )
                drawPath(
                    path = segment,
                    color = Color.Black,
                    style = Stroke(width = 1.dp.toPx())
                )

            }
        }


        // Text at the top
        Text(
            text = "N",
            modifier = Modifier.align(Alignment.TopCenter)
        )

        // Text at the bottom
        Text(
            text = "S",
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        // Text on the left
        Text(
            text = "W",
            modifier = Modifier.align(Alignment.CenterStart)
        )

        // Text on the right
        Text(
            text = "E",
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}