package ui.common.component.pager.carousel

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.common.component.pager.horizontal.HorizontalPagerIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(
    state: PagerState,
    modifier: Modifier = Modifier,
    horizontal: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    pageSize: PageSize = PageSize.Fill,
    beyondBoundsPageCount: Int = 1,
    pageSpacing: Dp = 0.dp,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    flingBehavior: SnapFlingBehavior = PagerDefaults.flingBehavior(state = state),
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    indicator: (@Composable BoxScope.() -> Unit)? = {
        val scope = rememberCoroutineScope()
        HorizontalPagerIndicator(
            state = state,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomCenter),
            onIndicatorClick = {
                scope.launch {
                    state.animateScrollToPage(it)
                }
            }
        )
    },
    label: (@Composable BoxScope.(Int) -> Unit)? = null,
    item: (Int) -> @Composable () -> Unit,
    autoScroll: Boolean = true,
    autoScrollDuration: Long = 1000L,
    animationTime: Int = 800,
) {

    if (autoScroll) {
        val isDragged by state.interactionSource.collectIsDraggedAsState()
        if (isDragged.not()) {
            with(state) {
                if (pageCount > 0) {
                    var currentPageKey by remember { mutableIntStateOf(0) }
                    LaunchedEffect(key1 = currentPageKey) {
                        launch {
                            delay(timeMillis = autoScrollDuration)
                            val nextPage = (currentPage + 1).mod(pageCount)
                            animateScrollToPage(
                                page = nextPage,
                                animationSpec = tween(
                                    durationMillis = animationTime
                                )
                            )
                            currentPageKey = nextPage
                        }
                    }
                }
            }
        }
    }

    Box(contentAlignment = Alignment.Center) {
        if (horizontal) {
            HorizontalPager(
                state,
                modifier,
                contentPadding,
                pageSize,
                beyondBoundsPageCount,
                pageSpacing,
                verticalAlignment,
                flingBehavior,
                userScrollEnabled,
                reverseLayout,
            ) { page: Int ->
                item(page)
            }
        } else {
            VerticalPager(
                state,
                modifier,
                contentPadding,
                pageSize,
                beyondBoundsPageCount,
                pageSpacing,
                horizontalAlignment,
                flingBehavior,
                userScrollEnabled,
                reverseLayout,
            ) { page ->
                item(page)
            }
        }
        indicator?.invoke(this)
        label?.invoke(this, state.currentPage)
    }
}