package ui.component.dropdown

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import ui.common.component.dropdown.model.DropdownItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContextMenu(
    modifier: Modifier = Modifier,
    items: List<DropdownItem>,
    onItemClick: (DropdownItem) -> Boolean,
    onDismissRequest: () -> Boolean,
): Modifier {

    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    DropdownMenu(
        expanded = isContextMenuVisible,
        onDismissRequest = {
            if (onDismissRequest()) {
                isContextMenuVisible = false
            }
        },
        offset = pressOffset.copy(
            y = pressOffset.y - itemHeight
        )
    ) {
        items.forEach {
            DropdownMenuItem(
                text = { it.text?.invoke() },
                leadingIcon = {
                    BadgedBox(
                        badge = {
                            it.badge?.invoke()
                        }
                    ) {
                        it.icon?.invoke()
                    }
                },
                trailingIcon = {
                    BadgedBox(
                        badge = {
                            it.trailingBadge?.invoke()
                        }
                    ) {
                        it.trailingIcon?.invoke()
                    }
                },
                onClick = {
                    if (onItemClick(it)) {
                        isContextMenuVisible = false
                    }
                })
        }
    }

    return modifier
        .indication(interactionSource, LocalIndication.current)
        .pointerInput(true) {
            awaitPointerEventScope {
                val event = awaitPointerEvent()
                Logger.i("Click:" + event.type)
                if (event.type == PointerEventType.Press &&
                    event.buttons.isSecondaryPressed
                ) {
//                    event.changes.forEach { e -> e.consume() }
                    // on-click logic here
                    isContextMenuVisible = true
                    val position = event.changes.first().position
                    pressOffset = DpOffset(position.x.toDp(), position.y.toDp())
                }
            }
            detectTapGestures(
                onLongPress = {
                    isContextMenuVisible = true
                    pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                },
                onPress = {
                    val press = PressInteraction.Press(it)
                    interactionSource.emit(press)
                    tryAwaitRelease()
                    interactionSource.emit(PressInteraction.Release(press))
                }
            )
        }
}