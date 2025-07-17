@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composeplayground.toolBar.collapsingToolBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.example.composeplayground.R
import kotlin.math.pow

//https://www.droidcon.com/2022/10/10/collapsing-toolbar-with-parallax-effect-and-curved-motion-in-jetpack-compose-%F0%9F%98%8E/
@Composable
fun CollapsingToolBar() {
    val scroll: ScrollState = rememberScrollState(0)
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { 56.dp.toPx() }
    
    Box(modifier = Modifier.fillMaxSize()) {
        Header(scroll, headerHeightPx)
        Body(scroll)
        Toolbar(scroll, headerHeightPx, toolbarHeightPx)
        Title(scroll, headerHeightPx, toolbarHeightPx)
    }
}

private val headerHeight = 275.dp

@Composable
private fun Header(scroll: ScrollState, headerHeightPx: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight)
            .graphicsLayer {
                // Parallax effect - header moves slower than scroll
                translationY = -scroll.value.toFloat() / 2f
                // Fade out effect
//                alpha = 1f - (scroll.value.toFloat() / headerHeightPx).coerceIn(0f, 1f)\
                alpha = (-1f / headerHeightPx) * scroll.value + 1
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Gradient overlay for better text readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000)),
                        startY = 3 * headerHeightPx / 4
                    )
                )
        )
    }
}

@Composable
private fun Body(scroll: ScrollState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scroll)
    ) {
        Spacer(Modifier.height(headerHeight))

        repeat(10) {
            Text(
                text = stringResource(R.string.lorem_ipsum),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier
//                    .background(Color(0XFF161616))
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun Toolbar(scroll: ScrollState, headerHeightPx: Float, toolbarHeightPx: Float) {
    val collapseRange = headerHeightPx - toolbarHeightPx
    val collapseFraction = (scroll.value / collapseRange).coerceIn(0f, 1f)
    
    TopAppBar(
        modifier = Modifier
            .background(Color(0xff026586))
            .alpha(collapseFraction)
        , // Fade in as we scroll
        navigationIcon = {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        },
        title = {},
    )
}
val titlePaddingStartPx = 16
val titlePaddingEndPx=56
@Composable
private fun Title(scroll: ScrollState, headerHeightPx: Float, toolbarHeightPx: Float) {
    var titleHeightPx by remember { mutableStateOf(0f) }

//    Text(
//        text = "New York",
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        modifier = Modifier
//            .graphicsLayer {
//                val collapseRange: Float = (headerHeightPx - toolbarHeightPx)
//                val collapseFraction: Float = (scroll.value / collapseRange).coerceIn(0f, 1f)
//
//                val titleY: Float = (1f-collapseFraction).pow(2) *
//                        (headerHeightPx - titleHeightPx - 16) +
//                        2 * collapseFraction * (1-collapseFraction) * headerHeightPx / 2 +
//                        collapseFraction.pow(2) * (toolbarHeightPx / 2 - titleHeightPx / 2)
//
//                val titleX: Float =
//                    (1f-collapseFraction).pow(2) * (titlePaddingStartPx) +
//                            2 * collapseFraction * (1-collapseFraction) * titlePaddingEndPx *
//                            5 / 4 + collapseFraction.pow(2) * titlePaddingEndPx
//
//
//                translationY = titleY
//                translationX = titleX
//            }
//            .onGloballyPositioned {
//                titleHeightPx = it.size.height.toFloat()
//            }
//    )
}
//@Composable
//private fun Title(scroll: ScrollState, headerHeightPx: Float, toolbarHeightPx: Float) {
//    var titleHeightPx by remember { mutableStateOf(0f) }
//    var titleWidthPx by remember { mutableStateOf(0f) }
//    val titleHeightDp = with(LocalDensity.current) { titleHeightPx.toDp() }
//
//    val collapseRange = headerHeightPx - toolbarHeightPx
//    val collapseFraction = (scroll.value / collapseRange).coerceIn(0f, 1f)
//
//    // Title position constants
//    val titlePaddingStart = 16.dp
//    val titlePaddingEnd = 56.dp
//
//    // Scale animation
//    val titleFontScaleStart = 1f
//    val titleFontScaleEnd = 0.66f
//    val scaleXY = lerp(
//        titleFontScaleStart.dp,
//        titleFontScaleEnd.dp,
//        collapseFraction
//    )
//
//    // Calculate extra padding for scale correction
//    val titleExtraStartPadding = (titleWidthPx / LocalDensity.current.density).dp * (1 - scaleXY.value) / 2
//
//    // Curved motion using quadratic Bézier curve
//    val titleXFirstInterpolatedPoint = lerp(
//        titlePaddingStart,
//        (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
//        collapseFraction
//    )
//
//    val titleXSecondInterpolatedPoint = lerp(
//        (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
//        titlePaddingEnd - titleExtraStartPadding,
//        collapseFraction
//    )
//
//    // Quadratic Bézier curve calculation
//    val t = collapseFraction
//    val titleX = (1 - t) * (1 - t) * titleXFirstInterpolatedPoint.value +
//                 2 * (1 - t) * t * titleXSecondInterpolatedPoint.value +
//                 t * t * (titlePaddingEnd - titleExtraStartPadding).value
//
//    val titleY = lerp(
//        0.dp,
//        ((toolbarHeightPx - titleHeightPx) / LocalDensity.current.density).dp,
//        collapseFraction
//    )
//
//    Text(
//        text = "New York",
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        color = Color.White,
//        modifier = Modifier
//            .graphicsLayer {
//                scaleX = scaleXY.value
//                scaleY = scaleXY.value
//                translationX = titleX
//                translationY = titleY.value
//            }
//            .onGloballyPositioned {
//                titleHeightPx = it.size.height.toFloat()
//                titleWidthPx = it.size.width.toFloat()
//            }
//    )
//}