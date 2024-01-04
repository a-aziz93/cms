package ui.component.pickerdialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import core.util.stringMatcher
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.component.search.SearchField

@OptIn(ExperimentalResourceApi::class)
@Composable
fun <T : Any> PickerDialog(
    items: List<T>,
    selectedItem: T = items.first(),
    onGetIcon: @Composable ((T) -> Unit)? = null,
    onItemClick: (T) -> Unit,
    search: Boolean = true,
    searchHint: String = "Search ...",
    rounded: Int = 12,
    onDismissRequest: () -> Unit,
) {
    var isPick by remember { mutableStateOf(selectedItem) }

    var searchValue by remember { mutableStateOf("") }

    var matchCase by remember { mutableStateOf(true) }
    var matchWord by remember { mutableStateOf(true) }
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
                    Row {
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
                            onMatchCase = {
                                matchCase = !matchCase
                                matchRegex = false
                            },
                            onMatchWord = {
                                matchWord = !matchWord
                                matchRegex = false
                            },
                            onMatchRegex = {
                                matchRegex = !matchRegex
                                matchCase = false
                                matchWord = false
                            },
                        )
                    }
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
                            if (onGetIcon != null) {
                                onGetIcon(item)
                            }
                            Text(
                                item.toString(),
                                Modifier.padding(horizontal = 18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

