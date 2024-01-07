package ui.common.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ui.common.component.dialog.model.PickerItem
import ui.common.component.search.SearchField
import ui.common.component.search.model.rememberSearchFieldState
import ui.common.model.SelectableColor

@Composable
fun <T : Any> PickerDialog(
    items: List<PickerItem<T>>,
    selectedItem: PickerItem<T> = items.first(),
    onItemClick: (PickerItem<T>) -> Unit,
    search: Boolean = true,
    searchHint: String = "Search ...",
    rounded: Int = 12,
    onDismissRequest: () -> Unit,
) {
    var isPick by remember { mutableStateOf(selectedItem) }

    val searchFieldState = rememberSearchFieldState()

    lateinit var matcher: (String, String) -> Boolean

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            shape = RoundedCornerShape(rounded.dp)
        ) {
            Column {
                if (search) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.White.copy(alpha = 0.1f)
                            )
                    ) {
                        SearchField(
                            state = searchFieldState,
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth(),
                            hint = searchHint,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            matchCaseColor = SelectableColor(
                                selectedColor = Color.Yellow,
                            ),
                            matchWordColor = SelectableColor(
                                selectedColor = Color.Yellow,
                            ),
                            matchRegexColor = SelectableColor(
                                selectedColor = Color.Yellow,
                            ),
                            onMatcher = {
                                matcher = it
                            }
                        )
                        Divider(thickness = 1.dp)
                    }
                }
                LazyColumn {
                    items(
                        (if (searchFieldState.searchTerm.isEmpty()) {
                            items
                        } else {
                            items.filter {
                                matcher(searchFieldState.searchTerm, it.toString())
                            }
                        })
                    ) { item ->
                        Row(
                            Modifier
                                .padding(
                                    horizontal = 18.dp,
                                    vertical = 18.dp
                                )
                                .clickable {
                                    onItemClick(item)
                                    isPick = item
                                    onDismissRequest()
                                }) {
                            item.icon?.invoke()
                            item.text?.invoke()
                        }
                    }
                }
            }
        }
    }
}

