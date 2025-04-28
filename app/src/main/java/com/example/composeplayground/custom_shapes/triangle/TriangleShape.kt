package com.example.composeplayground.custom_shapes.triangle


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

//class TriangleShape : Shape {
//    override fun createOutline(
//        size: Size,
//        layoutDirection: LayoutDirection,
//        density: Density
//    ): Outline {
//        val path = Path().apply {
//            val width = size.width
//            val height = size.height
//
//            // Calculate the points of the triangle relative to the center
//            val centerX = width/2
//            val centerY = height
//            val angle = 45.0
//            val radians = Math.toRadians(angle)
//            val x1 = centerX
//            val y1 = centerY - height / 2
//            val x2 = centerX - (width / 2 * cos(radians)).toFloat()
//            val y2 = centerY + (height / 2 * sin(radians)).toFloat()
//            val x3 = centerX + (width / 2 * cos(radians)).toFloat()
//            val y3 = y2
//
//            moveTo(x1, y1)
//            lineTo(x2, y2)
//            lineTo(x3, y3)
//            close()
//        }
//        return Outline.Generic(path)
//    }
//}
fun calculateBaseWidth(height: Float): Float {
    val tan67_5 = 2f + sqrt(2f)
    return 2f * height / tan67_5
}
class TriangleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val width = size.width
            val height = size.height

            // Calculate the points of the triangle relative to the center
            val centerX = width / 2
            val centerY = height
            val angle = 45.0
            val radians = Math.toRadians(angle)
            val x1 = centerX
            val y1 = centerY - height / 2
            val xPart = centerX - (width / 4 * cos(radians)).toFloat()
            val x2 = centerX - (width / 2 * cos(radians)).toFloat()
            val y2 = centerY + (height / 2 * sin(radians)).toFloat()
            val yPart = centerY + (height / 4 * sin(radians)).toFloat()
            val x3 = centerX + (width / 2 * cos(radians)).toFloat()
            val y3 = y2

            moveTo(x1, y1)
            lineTo(x2, y2)
            lineTo(x3, y3)
            close()

            // Divide the triangle into three equal horizontal parts
            val partHeight = height
            // Draw a black line inside the triangle
            moveTo(centerX, y1)
            lineTo(centerX, y2)
// Draw a horizontal black line inside the triangle
            moveTo(xPart, y1 + calculateBaseWidth(partHeight ))
            lineTo(x3, y1 + calculateBaseWidth(height))
        }
        return Outline.Generic(path)
    }
}
@Preview
@Composable
fun TriangleView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(300.dp)) {
            val outline = TriangleShape().createOutline(
                size = size,
                layoutDirection = LayoutDirection.Ltr,
                density = Density(density)
            )
            if (outline is Outline.Generic) {
                drawPath(
                    path = outline.path,
                    color = Color.Green
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TriangleView1(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(300.dp)) {
            val outline = TriangleShape().createOutline(
                size = size,
                layoutDirection = LayoutDirection.Ltr,
                density = Density(density)
            )
            if (outline is Outline.Generic) {
                val path = outline.path

                // Draw the triangle with black borders
                drawPath(path = path, color = Color.Green)
                drawPath(path = path, color = Color.Black, style = Stroke(width = 2.dp.toPx()))

//                // Draw the horizontal lines
//                val partHeight = size.height / 3
//                drawLine(
//                    color = Color.Black,
//                    start = androidx.compose.ui.geometry.Offset(0f, partHeight),
//                    end = androidx.compose.ui.geometry.Offset(size.width, partHeight),
//                    strokeWidth = 2.dp.toPx()
//                )
//                drawLine(
//                    color = Color.Black,
//                    start = androidx.compose.ui.geometry.Offset(0f, 2 * partHeight),
//                    end = androidx.compose.ui.geometry.Offset(size.width, 2 * partHeight),
//                    strokeWidth = 2.dp.toPx()
//                )
            }
        }
    }
}
@Preview
@Composable
fun TriangleView2(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(300.dp)) {
            val outline = TriangleShape().createOutline(
                size = size,
                layoutDirection = LayoutDirection.Ltr,
                density = Density(density)
            )
            if (outline is Outline.Generic) {
                drawPath(
                    path = outline.path,
                    color = Color.Green
                )
            }
        }
        Canvas(modifier = Modifier.size(220.dp)) {
            val outline = TriangleShape().createOutline(
                size = size,
                layoutDirection = LayoutDirection.Ltr,
                density = Density(density)
            )
            if (outline is Outline.Generic) {
                drawPath(
                    path = outline.path,
                    color = Color.Yellow
                )
            }
        }
        Canvas(modifier = Modifier.size(140.dp)) {
            val outline = TriangleShape().createOutline(
                size = size,
                layoutDirection = LayoutDirection.Ltr,
                density = Density(density)
            )
            if (outline is Outline.Generic) {
                drawPath(
                    path = outline.path,
                    color = Color.Red
                )
            }
        }
    }
}