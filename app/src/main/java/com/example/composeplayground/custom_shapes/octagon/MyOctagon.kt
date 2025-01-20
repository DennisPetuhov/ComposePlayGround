package com.example.composeplayground.custom_shapes.octagon

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
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


class OctagonShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val width = size.width
            val height = size.height
            val centerX = width / 2
            val centerY = height / 2
            val radius = minOf(width, height) / 2
            val angleStep = 2 * PI / 8

            for (i in 0 until 8) {
                val angle = i * angleStep
                val x = centerX + radius * cos(angle).toFloat()
                val y = centerY + radius * sin(angle).toFloat()
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }
        return Outline.Generic(path)
    }

    fun createSegments(size: Size): List<Segment> {
        val segments = mutableListOf<Segment>()
        val width = size.width
        val height = size.height
        val centerX = width / 2
        val centerY = height / 2
        val radius = minOf(width, height) / 2
        val angleStep = 2 * PI / 8

        for (i in 0 until 8) {
            val path = Path().apply {
                moveTo(centerX, centerY)
                val angle1 = i * angleStep
                val x1 = centerX + radius * cos(angle1).toFloat()
                val y1 = centerY + radius * sin(angle1).toFloat()
                lineTo(x1, y1)
                val angle2 = (i + 1) * angleStep
                val x2 = centerX + radius * cos(angle2).toFloat()
                val y2 = centerY + radius * sin(angle2).toFloat()
                lineTo(x2, y2)
                close()
            }
            segments.add(Segment(path, Color.Unspecified))
        }
        return segments
    }
}

data class Segment(val path: Path, val color: Color)

@Composable
fun OctagonView1(modifier: Modifier = Modifier, colors: List<Color>) {
    Box(modifier = modifier.size(300.dp)) {
        Canvas(modifier = Modifier.matchParentSize().align(Alignment.Center)) {
            val size = size
            val octagonShape = OctagonShape()
            val segments = octagonShape.createSegments(size)
            segments.forEachIndexed { index, segment ->
                drawPath(
                    path = segment.path,
                    color = colors.getOrElse(index) { Color.Gray }
                )
            }
        }
        Canvas(modifier = Modifier.size(150.dp).align(Alignment.Center)) {
            val a = colors.shuffled()
            val size = size
            val octagonShape = OctagonShape()
            val segments = octagonShape.createSegments(size)
            segments.forEachIndexed { index, segment ->
                drawPath(
                    path = segment.path,

                color = a.getOrElse(index) { Color.Gray }
                )
            }
        }
    }
}