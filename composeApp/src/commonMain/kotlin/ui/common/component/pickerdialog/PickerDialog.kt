package ui.common.component.pickerdialog

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
import core.util.stringMatcher
import ui.common.component.pickerdialog.model.PickerItem
import ui.common.component.search.SearchField
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

    var searchValue by remember { mutableStateOf("") }

    var matchCase by remember { mutableStateOf(false) }
    var matchWord by remember { mutableStateOf(false) }
    var matchRegex by remember { mutableStateOf(false) }


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
                    SearchField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        value = searchValue,
                        onValueChange = {
                            searchValue = it
                        },
                        fontSize = 14.sp,
                        hint = searchHint,
                        textAlign = TextAlign.Start,
                        matchCase = matchCase,
                        matchCaseColor = SelectableColor(
                            selectedColor = Color.Yellow,
                        ),
                        onMatchCase = {
                            matchCase = !matchCase
                            matchRegex = false
                        },
                        matchWord = matchWord,
                        matchWordColor = SelectableColor(
                            selectedColor = Color.Yellow,
                        ),
                        onMatchWord = {
                            matchWord = !matchWord
                            matchRegex = false
                        },
                        matchRegex = matchRegex,
                        matchRegexColor = SelectableColor(
                            selectedColor = Color.Yellow,
                        ),
                        onMatchRegex = {
                            matchRegex = !matchRegex
                            matchCase = false
                            matchWord = false
                        },
                    )
                }
                Divider(thickness = 1.dp)
                LazyColumn {
                    items(
                        (if (searchValue.isEmpty()) {
                            items
                        } else {
                            val matcher = stringMatcher(matchCase, matchWord, matchRegex)
                            items.filter {
                                matcher(searchValue, it.toString())
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
                            item.icon?.let { it() }
                            item.text?.let { it() }
                        }
                    }
                }
            }
        }
    }
}

