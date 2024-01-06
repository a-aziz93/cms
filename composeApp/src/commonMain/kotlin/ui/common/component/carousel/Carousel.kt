package ui.common.component.carousel

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import core.util.carouselTransition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun Carousel(
    horizontal: Boolean = true,
    padding: Dp = 0.dp,
    spacing: Dp = 0.dp,
    carouselLabel: @Composable () -> Unit = {},
    title: @Composable () -> Unit = {},
    text: @Composable () -> Unit = {},
    items: List<@Composable () -> Unit>,
    onItemClicked: () -> Unit,
    autoScrollDuration: Long = 3000L,
    animationTime: Int = 800,
) {
    val pagerState: PagerState = rememberPagerState(pageCount = { items.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
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
            if(horizontal){
                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(
                        horizontal = padding
                    ),
                    pageSpacing = spacing
                ) { page: Int ->
                    CarouselContent(
                        modifier = Modifier.carouselTransition(
                            page,
                            pagerState
                        ),
                        title,
                        text,
                        { items[page]() },
                        onItemClicked,
                    )
                }
            }
            else {
                VerticalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(
                        horizontal = padding
                    ),
                    pageSpacing = spacing
                ) { page ->
                    CarouselContent(
                        modifier = Modifier.carouselTransition(
                            page,
                            pagerState
                        ),
                        title,
                        text,
                        { items[page]() },
                        onItemClicked,
                    )
                }
            }
        }
        carouselLabel()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CarouselContent(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    text: @Composable () -> Unit = {},
    item: @Composable () -> Unit,
    onItemClicked: () -> Unit,
) {
    Card(
        onClick = onItemClicked,
        modifier = modifier,
    ) {
        Box {
            item()
            title()
            text()
        }
    }
}