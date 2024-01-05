package ui.settings.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.model.Item

@Composable
fun SettingsListDropdown(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    state: SettingValueState<Int>,
    title: @Composable () -> Unit,
    items: List<Item>,
    icon: (@Composable () -> Unit)? = null,
    subtitle: (@Composable () -> Unit)? = null,
    onItemSelected: ((Item) -> Unit)? = null,
) {
    if (state.value > items.size) {
        throw IndexOutOfBoundsException("Current value of state for list setting cannot be grater than items size")
    }
    val scrollState = rememberScrollState()

    Surface {
        var isDropdownExpanded by remember { mutableStateOf(false) }

        Row(
            modifier = modifier.fillMaxWidth()
                .clickable(enabled = enabled) { isDropdownExpanded = true },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SettingsTileScaffold(
                enabled = enabled,
                title = title,
                subtitle = subtitle,
                icon = icon,
                action = {
                    WrapContentColor(enabled = enabled) {
                        Column(
                            modifier = Modifier.padding(end = 8.dp)
                                .verticalScroll(scrollState),
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                val item = items[state.value]
                                item.icon?.let { icon -> icon() }
                                item.text?.let { text -> text() }
                                Icon(
                                    modifier = Modifier.padding(start = 8.dp),
                                    imageVector = Icons.Outlined.ArrowDropDown,
                                    contentDescription = null,
                                )
                            }

                            DropdownMenu(
                                expanded = isDropdownExpanded,
                                onDismissRequest = { isDropdownExpanded = false },
                            ) {
                                items.forEachIndexed { index, item ->
                                    DropdownMenuItem(
                                        leadingIcon = {
                                            item.icon?.let { icon -> icon() }
                                        },
                                        text = {
                                            item.text?.let { text -> text() }
                                        },
                                        onClick = {
                                            state.value = index
                                            isDropdownExpanded = false
                                            onItemSelected?.invoke(item)
                                        },
                                    )
                                }
                            }
                        }
                    }
                },
            )
        }
    }
}
