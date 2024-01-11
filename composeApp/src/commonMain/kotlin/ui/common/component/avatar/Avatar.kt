package ui.common.component.avatar

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.component.dropdown.ContextMenu
import ui.common.component.dropdown.model.DropdownItem
import ui.common.model.Item
import kotlin.math.absoluteValue

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    resource: Resource<Painter>? = null,
    onResourceLoading: @Composable (BoxScope.(Float) -> Unit)? = { painterResource("drawable/image_loading.png") },
    onResourceFailure: @Composable (BoxScope.(Throwable) -> Unit)? = { painterResource("drawable/image_load_error.png") },
    contentDescription: String = "",
    size: Dp = 40.dp,
    firstName: String,
    lastName: String,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    contextMenuItems: List<ContextMenuItem> = emptyList(),
) =
    ContextMenuDataProvider(items = { contextMenuItems }) {
        ContextMenuArea(items = { emptyList() }) {
            if (resource == null)
                InitialsAvatar(
                    modifier,
                    firstName,
                    lastName,
                    textStyle
                ) else ImageAvatar(
                modifier,
                resource,
                onResourceLoading,
                onResourceFailure,
                contentDescription,
            )
        }
    }

@Composable
private fun InitialsAvatar(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        val color = remember(firstName, lastName) {
            val name = listOf(firstName, lastName)
                .joinToString(separator = "")
                .uppercase()
            Color((name.fold(0) { acc, char -> char.code + acc } / (name.length * 1000)).absoluteValue.toFloat(),
                0.5f,
                0.4f)
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(SolidColor(color))
        }
        Text(text = (firstName.take(1) + lastName.take(1)).uppercase(), style = textStyle, color = Color.White)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ImageAvatar(
    modifier: Modifier = Modifier,
    resource: Resource<Painter>,
    onBackgroundLoading: @Composable (BoxScope.(Float) -> Unit)? = { painterResource("drawable/image_loading.png") },
    onBackgroundFailure: @Composable (BoxScope.(Throwable) -> Unit)? = { painterResource("drawable/image_load_error.png") },
    contentDescription: String = "",
) {
    KamelImage(
        modifier = modifier,
        resource = resource,
        contentDescription = contentDescription,
        onLoading = onBackgroundLoading,
        onFailure = onBackgroundFailure,
        contentScale = ContentScale.FillBounds,
    )
}