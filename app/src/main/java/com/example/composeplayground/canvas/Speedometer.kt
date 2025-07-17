package com.example.composeplayground.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.lang.Math.toRadians
import kotlin.math.cos
import kotlin.math.sin

@Preview
@Composable
fun ProblemSensitivity(
    trackColor: Color = Color.Blue,
    inputValue: Int = 80,
    stroke: Dp = 16.dp,
    modifier: Modifier = Modifier,
) {
    val meterValue = getMeterValue(inputValue)
    Box(modifier = modifier.size(96.dp)) {
        Canvas(modifier = Modifier.size(96.dp)) {
            val height = size.height
            val width = size.width
            val startAngle = 180f

            drawArc(
                color = trackColor,
                startAngle = startAngle,
                sweepAngle = 40f,
                useCenter = false,
                topLeft = Offset(stroke.toPx() / 2, height / 5 + stroke.toPx() / 2),
                size = Size(width - stroke.toPx(), width - stroke.toPx()),

                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Butt)
            )
            drawArc(
                color = trackColor,
                startAngle = 225f,
                sweepAngle = 40f,
                useCenter = false,
                topLeft = Offset(stroke.toPx() / 2, height / 5 + stroke.toPx() / 2),
                size = Size(width - stroke.toPx(), width - stroke.toPx()),

                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Butt)
            )

            drawArc(
                color = trackColor,
                startAngle = 270f,
                sweepAngle = 40f,
                useCenter = false,
                topLeft = Offset(stroke.toPx() / 2, height / 5 + stroke.toPx() / 2),
                size = Size(width - stroke.toPx(), width - stroke.toPx()),

                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Butt)
            )
            drawArc(
                color = trackColor,
                startAngle = 315f,
                sweepAngle = 45f,
                useCenter = false,
                topLeft = Offset(stroke.toPx() / 2, height / 5 + stroke.toPx() / 2),
                size = Size(width - stroke.toPx(), width - stroke.toPx()),

                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Butt)
            )
            drawCircle(
                color = Color.Blue,
                radius = 14f,
                center = Offset(width / 2f, height/1.5f),
                style = Stroke(width = 10.dp.toPx())

            )

            val centerOffset = Offset(width / 2f, height/1.5f)


//             Calculate needle angle based on inputValue
//            val needleAngle = (meterValue / 100f) * sweepAngle + startAngle
            val needleAngle = startAngle + (meterValue / 100f) * startAngle
            val needleLength = 80f // Adjust this value to control needle length
            val needleBaseWidth = 15f // Adjust this value to control the base width


            val needlePath = Path().apply {
                // Calculate the top point of the needle
                val topX = centerOffset.x + needleLength * cos(
                    toRadians(needleAngle.toDouble()).toFloat()
                )
                val topY = centerOffset.y + needleLength * sin(
                    toRadians(needleAngle.toDouble()).toFloat()
                )

                // Calculate the base points of the needle
                val baseLeftX = centerOffset.x + needleBaseWidth * cos(
                    toRadians((needleAngle - 90).toDouble()).toFloat()
                )
                val baseLeftY = centerOffset.y + needleBaseWidth * sin(
                    toRadians((needleAngle - 90).toDouble()).toFloat()
                )
                val baseRightX = centerOffset.x + needleBaseWidth * cos(
                    toRadians((needleAngle + 90).toDouble()).toFloat()
                )
                val baseRightY = centerOffset.y + needleBaseWidth * sin(
                    toRadians((needleAngle + 90).toDouble()).toFloat()
                )

                moveTo(topX, topY)
                lineTo(baseLeftX, baseLeftY)
                lineTo(baseRightX, baseRightY)
                close()
            }

            drawPath(
                color = trackColor,
                path = needlePath
            )
        }
    }
}


private fun getMeterValue(inputPercentage: Int): Int {
    return if (inputPercentage < 0) {
        0
    } else if (inputPercentage > 100) {
        100
    } else {
        inputPercentage
    }
}
