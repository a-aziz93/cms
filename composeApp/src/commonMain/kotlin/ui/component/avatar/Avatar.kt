package ui.component.avatar

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.kamel.core.Resource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun InitialsAvatar(
    resource: Resource<Painter>? = null,
    onResourceLoading: @Composable (BoxScope.(Float) -> Unit)? = { painterResource("drawable/image_loading.png") },
    onResourceFailure: @Composable (BoxScope.(Throwable) -> Unit)? = { painterResource("drawable/image_load_error.png") },
    contentDescription: String = "",
    size: Dp = 40.dp,
    firstName: String,
    lastName: String,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    onClick: (() -> Unit)? = null,
) = if (resource == null) InitialsAvatar(
    firstName,
    lastName,
    size,
    textStyle,
    onClick
) else ImageAvatar(resource, onResourceLoading, onResourceFailure, contentDescription, size, onClick)