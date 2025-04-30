package com.apps.histopia.widget


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import io.eyram.iconsax.IconSax


/**
 * Common animation specifications
 */
private object IconAnimationSpecs {
    val ScaleAnimation = tween<Float>(
        durationMillis = 200,
        easing = FastOutSlowInEasing
    )

    val FillAnimation = tween<Float>(
        durationMillis = 300
    )
}

/**
 * Common icon sizes and measurements
 */
private object IconDimensions {
    val HomeSize = DpSize(52.dp, 52.dp)
    val LoveSize = DpSize(32.dp, 32.dp)
    val ProfileSize = DpSize(32.dp, 32.dp)

    const val strokeWidth = 4f
    const val thinStrokeWidth = 1.5f
}

/**
 * Base composable for animated icon container
 */
@Composable
private fun AnimatedIconContainer(
    modifier: Modifier = Modifier,
    isAnimating: Boolean,
    size: DpSize,
    content: @Composable () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isAnimating) 0.8f else 1f,
        animationSpec = IconAnimationSpecs.ScaleAnimation
    )

    Box(
        modifier = modifier
            .size(size)
            .scale(scale)
    ) {
        content()
    }
}


@Composable
fun CustomHomeIcon(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFE4BE9E),
    starColor: Color = Color(0xFF579A35),
    isActive: Boolean = false,

    ) {
    var isAnimating by remember { mutableStateOf(false) }

    AnimatedIconContainer(
        modifier = modifier,
        isAnimating = isAnimating,
        size = IconDimensions.HomeSize,
    ) {
        HomeIconCanvas(
            backgroundColor = backgroundColor,
            starColor = starColor,

        )
    }
}


@Composable
fun CustomLoveIcon(
    modifier: Modifier = Modifier,
    activeColor: Color = Color.White,
    inactiveColor: Color = Color.Black,
    isActive: Boolean = false,
) {
    var isPulsing by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPulsing) 1.2f else 1f,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ), finishedListener = {
            isPulsing = false
        }, label = "pulse")
    LaunchedEffect(isActive) {
        isPulsing = isActive
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            painterResource(IconSax.Bold.Lovely),
            contentDescription = "Love",
            tint = if (isActive) activeColor else inactiveColor,
            modifier = Modifier
                .scale(
                    (if (isActive) scale else 1f)
                )
                .size(IconDimensions.LoveSize)
        )
    }
}

@Composable
fun CustomProfileIcon(
    modifier: Modifier = Modifier,
    activeColor: Color = Color.White,
    inactiveColor: Color = Color.Black,
    isActive: Boolean = false,
) {
    var isPulsing by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPulsing) 1.2f else 1f,
        animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            ), finishedListener = {
                isPulsing = false
        }, label = "pulse")
    LaunchedEffect(isActive) {
        isPulsing = isActive
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            painterResource(IconSax.Bold.Profile2user),
            contentDescription = "Profile",
            tint = if (isActive) activeColor else inactiveColor,
            modifier = Modifier
                .scale(
                    (if (isActive) scale else 1f)
                )
                .size(IconDimensions.ProfileSize)
        )
    }
}


@Composable
private fun HomeIconCanvas(
    backgroundColor: Color,
    starColor: Color,
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val scale = minOf(size.width / 28f, size.height / 31f)


        val rectSize = Size(19f * scale, 19f * scale)
        drawRoundRect(
            color = backgroundColor,
            topLeft = Offset(
                x = centerX - (rectSize.width / 2),
                y = centerY - (rectSize.height / 2)
            ),
            size = rectSize,
            cornerRadius = CornerRadius(7.5f * scale),
            style = Fill
        )

        drawPath(
            path = createStarPath(Offset(centerX, centerY), scale),
            color = starColor,
            style = Fill
        )
    }
}


private fun createStarPath(center: Offset, scale: Float): Path = Path().apply {
    val points = listOf(
        Offset(0f, -10f),
        Offset(1.7143f, -5.36709f),
        Offset(5.3671f, -1.7143f),
        Offset(10f, 0f),
        Offset(5.3671f, 1.7143f),
        Offset(1.7143f, 5.3671f),
        Offset(0f, 10f),
        Offset(-1.7143f, 5.3671f),
        Offset(-5.3671f, 1.7143f),
        Offset(-10f, 0f),
        Offset(-5.3671f, -1.7143f),
        Offset(-1.7143f, -5.36709f)
    )

    moveTo(center.x + (points[0].x * scale), center.y + (points[0].y * scale))
    points.forEach { point ->
        lineTo(center.x + (point.x * scale), center.y + (point.y * scale))
    }
    close()
}

