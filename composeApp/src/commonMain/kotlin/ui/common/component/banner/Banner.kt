package ui.common.component.banner

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Banner(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    text: @Composable () -> Unit = {},
    resource: Resource<Painter>,
    onResourceLoading: @Composable (BoxScope.(Float) -> Unit)? = { painterResource("drawable/image_loading.png") },
    onResourceFailure: @Composable (BoxScope.(Throwable) -> Unit)? = { painterResource("drawable/image_load_error.png") },
) {
    KamelImage(
        resource = resource,
        contentDescription = null,
        onLoading = onResourceLoading,
        onFailure = onResourceFailure,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
    )
    title()
    text()
}