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
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import ui.component.navigation.navigationTextColor
import ui.model.dropdown.DropDownItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dropdown(
    items: List<DropDownItem>,
    dismiss: () -> Boolean,
    onClick: (DropDownItem) -> Boolean,
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

    val density = LocalDensity.current

    val modifier = Modifier
        .indication(interactionSource, LocalIndication.current)
        .pointerInput(true) {
            awaitPointerEventScope {
                val event = awaitPointerEvent()
                if (event.type == PointerEventType.Press &&
                    event.buttons.isSecondaryPressed
                ) {
                    event.changes.forEach { e -> e.consume() }
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

    Card(
//        elevation = 4.dp,
        modifier = Modifier
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            }
    ) {
        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = {
                if (dismiss()) {
                    isContextMenuVisible = false
                }
            },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            )
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = { it.text?.let { text -> Text(text = text) } },
                    leadingIcon = {
                        BadgedBox(
                            badge = {
                                if (it.badge != null) {
                                    Badge {
                                        Text(it.badge)
                                    }
                                }
                            }
                        ) {
                            it.icon?.let { it1 -> Icon(imageVector = it1, contentDescription = null) }
                        }
                    },
                    onClick = {
                        if (onClick(it)) {
                            isContextMenuVisible = false
                        }
                    })
            }
        }
    }

    return modifier
}