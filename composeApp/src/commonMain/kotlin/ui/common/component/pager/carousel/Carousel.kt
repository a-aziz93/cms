package ui.common.component.pager.carousel

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import core.util.carouselTransition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.common.component.pager.HorizontalPagerIndicator

@OptIn(
    ExperimentalFoundationApi::class,
)
@Composable
fun Carousel(
    carouselLabel: (@Composable () -> Unit)? = null,
    title: (@Composable () -> Unit)? = null,
    text: (@Composable () -> Unit)? = null,
    items: List<@Composable () -> Unit>,
    onItemClicked: () -> Unit,
    state: PagerState = rememberPagerState(pageCount = { items.size }),
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
    indicator: (@Composable () -> Unit)? = {
        val scope = rememberCoroutineScope()
        HorizontalPagerIndicator(
            state = state,
            onIndicatorClick = {
                scope.launch {
                    state.animateScrollToPage(it)
                }
            }
        )
    },
    autoScrollDuration: Long = 1000L,
    animationTime: Int = 800,
) {

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

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
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
                    CarouselContent(
                        state,
                        page,
                        title,
                        text,
                        { items[page]() },
                        onItemClicked,
                    )
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
                    CarouselContent(
                        state,
                        page,
                        title,
                        text,
                        { items[page]() },
                        onItemClicked,
                    )
                }
            }
        }
        indicator?.invoke()
        carouselLabel?.invoke()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CarouselContent(
    state: PagerState,
    page: Int,
    title: (@Composable () -> Unit)? = null,
    text: (@Composable () -> Unit)? = null,
    item: @Composable () -> Unit,
    onItemClicked: () -> Unit,
) {
    Card(
        onClick = onItemClicked,
        modifier = Modifier.carouselTransition(
            page,
            state
        ),
    ) {
        Box {
            item()
            title?.invoke()
            text?.invoke()
        }
    }
}