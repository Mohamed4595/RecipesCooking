package com.mhmd.recipescooking.framework.presentation.recipe

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingRecipeShimmer(
    imageHeight: Dp,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val cardWidthPx = with(LocalDensity.current) { (maxWidth).toPx() }
        val cardHeightPx = with(LocalDensity.current) { (imageHeight).toPx() }
        val gradientWidth: Float = (0.2f * cardHeightPx)

        val infiniteTransition = rememberInfiniteTransition()
        val xCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = (cardWidthPx + gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1300,
                    easing = LinearEasing,
                    delayMillis = 300
                ),
                repeatMode = RepeatMode.Restart
            )
        )
        val yCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = (cardHeightPx + gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1300,
                    easing = LinearEasing,
                    delayMillis = 300
                ),
                repeatMode = RepeatMode.Restart
            )
        )

        val colors = listOf(
            MaterialTheme.colors.surface.copy(alpha = .9f),
            MaterialTheme.colors.onBackground.copy(alpha = .3f),
            MaterialTheme.colors.surface.copy(alpha = .9f),
        )

        val brush = Brush.linearGradient(
            colors,
            start = Offset(xCardShimmer.value - gradientWidth, yCardShimmer.value - gradientWidth),
            end = Offset(xCardShimmer.value, yCardShimmer.value)
        )
        Column {
            Surface(
                shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight)
                        .background(brush = brush)
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 8)
                        .background(brush = brush)
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 32.dp, end = 100.dp, start = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 15)
                        .background(brush = brush)
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, end = 176.dp, start = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 15)
                        .background(brush = brush)
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, end = 156.dp, start = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 15)
                        .background(brush = brush)
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, end = 136.dp, start = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 15)
                        .background(brush = brush)
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, end = 116.dp, start = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 15)
                        .background(brush = brush)
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, end = 96.dp, start = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 15)
                        .background(brush = brush)
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, end = 76.dp, start = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 15)
                        .background(brush = brush)
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, end = 56.dp, start = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 15)
                        .background(brush = brush)
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, end = 36.dp, start = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 15)
                        .background(brush = brush)
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, end = 16.dp, start = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 15)
                        .background(brush = brush)
                )
            }
        }
    }


}