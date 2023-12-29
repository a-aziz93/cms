package ui.component.avatar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageAvatar(
    resource: Resource<Painter>,
    onBackgroundLoading: @Composable (BoxScope.(Float) -> Unit)? = { painterResource("drawable/image_loading.png") },
    onBackgroundFailure: @Composable (BoxScope.(Throwable) -> Unit)? = { painterResource("drawable/image_load_error.png") },
    contentDescription: String = "",
    size: Dp = 50.dp,
    onClick: (() -> Unit)? = null,
) {
    var modifier = Modifier
        .clip(shape = CircleShape)
        .size(size)
    if (onClick != null) {
        modifier = modifier.clickable {
            onClick()
        }
    }
    KamelImage(
        modifier = modifier,
        resource = resource,
        contentDescription = contentDescription,
        onLoading = onBackgroundLoading,
        onFailure = onBackgroundFailure,
        contentScale = ContentScale.FillBounds,
    )
}