package core.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import kotlin.math.absoluteValue

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

fun stringMatcher(
    caseSensitive: Boolean = true,
    wholeWord: Boolean = true,
    regex: Boolean = false,
): (String, String) -> Boolean =
    if (regex) {
        { str, pattern -> Regex(pattern).matches(str) }
    } else if (wholeWord) { str1, str2 -> str1.equals(str2, !caseSensitive) } else { str1, str2 ->
        str2.contains(
            str1,
            !caseSensitive
        )
    }