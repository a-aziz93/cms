package core.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import kotlin.math.absoluteValue

fun String.toHslColor(saturation: Float = 0.5f, lightness: Float = 0.4f): Color {
    val hue = fold(0) { acc, char -> char.code + acc } / (length * 1000)
    return Color(hue.absoluteValue.toFloat(), saturation, lightness)
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition(
    page: Int,
    pagerState: PagerState
) = graphicsLayer {
    val pageOffset =
        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

    val transformation = lerp(
        start = Shadow(blurRadius = 0.8f),
        stop = Shadow(blurRadius = 1f),
        fraction = 1f - pageOffset.coerceIn(
            0f,
            1f
        )
    )
    alpha = transformation.blurRadius
    scaleY = transformation.blurRadius
}