package ui.component.avatar

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import core.util.toHslColor
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.component.dropdown.Dropdown
import ui.component.navigation.navigationBadgeColor
import ui.component.navigation.navigationIconColor
import ui.model.dropdown.DropDownItem

@OptIn(ExperimentalResourceApi::class, ExperimentalFoundationApi::class)
@Composable
fun Avatar(
    resource: Resource<Painter>? = null,
    onResourceLoading: @Composable (BoxScope.(Float) -> Unit)? = { painterResource("drawable/image_loading.png") },
    onResourceFailure: @Composable (BoxScope.(Throwable) -> Unit)? = { painterResource("drawable/image_load_error.png") },
    contentDescription: String = "",
    size: Dp = 40.dp,
    firstName: String,
    lastName: String,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    contextMenuItems: List<DropDownItem> = emptyList(),
    onItemClick: (DropDownItem) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {

    val modifier = Dropdown(
        items = contextMenuItems,
        dismiss = { true },
        onClick = {
            true
        }
    )

    if (resource == null) InitialsAvatar(
        modifier,
        firstName,
        lastName,
        size,
        textStyle
    ) else ImageAvatar(
        modifier,
        resource,
        onResourceLoading,
        onResourceFailure,
        contentDescription,
        size
    )
}

@Composable
private fun InitialsAvatar(
    modifier: Modifier,
    firstName: String,
    lastName: String,
    size: Dp = 40.dp,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
) {
    Box(
        modifier
            .clip(shape = CircleShape)
            .size(size), contentAlignment = Alignment.Center
    ) {
        val color = remember(firstName, lastName) {
            val name = listOf(firstName, lastName)
                .joinToString(separator = "")
                .uppercase()
            name.toHslColor()
        }
        val initials = (firstName.take(1) + lastName.take(1)).uppercase()
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(SolidColor(color))
        }
        Text(text = initials, style = textStyle, color = Color.White)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ImageAvatar(
    modifier: Modifier,
    resource: Resource<Painter>,
    onBackgroundLoading: @Composable (BoxScope.(Float) -> Unit)? = { painterResource("drawable/image_loading.png") },
    onBackgroundFailure: @Composable (BoxScope.(Throwable) -> Unit)? = { painterResource("drawable/image_load_error.png") },
    contentDescription: String = "",
    size: Dp = 50.dp,
) {
    KamelImage(
        modifier = modifier
            .clip(shape = CircleShape)
            .size(size),
        resource = resource,
        contentDescription = contentDescription,
        onLoading = onBackgroundLoading,
        onFailure = onBackgroundFailure,
        contentScale = ContentScale.FillBounds,
    )
}