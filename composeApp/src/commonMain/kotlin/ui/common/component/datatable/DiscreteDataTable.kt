package ui.common.component.datatable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.TableColumnWidth
import com.seanproctor.datatable.material3.Material3CellContentProvider
import com.seanproctor.datatable.paging.BasicPaginatedDataTable
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.*
import ui.common.component.datatable.model.TableColumn
import kotlin.reflect.full.declaredMemberProperties

@Composable
fun DiscreteDataTable(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    commonActions: Boolean = true,
    headerHeight: Dp = 56.dp,
    rowHeight: Dp = 52.dp,
    rowSeparator: @Composable (rowIndex: Int) -> Unit = { Divider() },
    horizontalPadding: Dp = 16.dp,
    columns: List<TableColumn>,
    pageCount: Int,
    pageSize: Int = 10,
    pageIndex: Int = 0,
    items: (Int) -> List<Any>,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (commonActions) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center),
                ) {
                    title()
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                ) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(imageVector = EvaIcons.Outline.Search, contentDescription = null)
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(imageVector = EvaIcons.Outline.Save, contentDescription = null)
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(imageVector = EvaIcons.Outline.Plus, contentDescription = null)
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(imageVector = EvaIcons.Outline.Edit, contentDescription = null)
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(imageVector = EvaIcons.Outline.Trash2, contentDescription = null)
                    }
                }
            }
        }

        val state = rememberPaginatedDataTableState(pageSize, pageIndex)

        BasicPaginatedDataTable(
            columns = listOf(DataColumn(
                alignment = Alignment.CenterHorizontally,
                width = TableColumnWidth.Fixed(100.dp),
            ) {
                Checkbox(checked = false, onCheckedChange = {

                })
            }) + columns.map {
                DataColumn(alignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {}) {
                        Icon(imageVector = EvaIcons.Outline.ChevronUp, contentDescription = null)
                    }
                    Text(it.headerName)
                }
            } + DataColumn(
                alignment = Alignment.CenterHorizontally,
                width = TableColumnWidth.Fixed(120.dp),
            ) {
                Text("Actions")
            },
            modifier = modifier,
            separator = rowSeparator,
            headerHeight = headerHeight,
            horizontalPadding = horizontalPadding,
            state = state,
            footer = {
                Paginator(
                    modifier = Modifier
                        .height(rowHeight)
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    pageCount = state.count,
                    pageSize = state.pageSize,
                    pageIndex = state.pageIndex,
                    maxPageCount = 5,
                    onFirstPageClick = {
                        state.pageIndex = 0
                    },
                    onPrevPageClick = {
                        state.pageIndex--
                    },
                    onPageClick = {
                        state.pageIndex = it
                    },
                    onNextPageClick = {
                        state.pageIndex++
                    },
                    onLastPageClick = {
                        state.pageIndex = state.count - 1
                    }
                )
            },
            cellContentProvider = Material3CellContentProvider,
        ) {
            row {
                cell {
                    Text("Search")
                }
                columns.map {
                    cell {
                        OutlinedTextField(value = "", onValueChange = {})
                    }
                }
            }
            items(state.pageIndex).map { item ->
                row {
                    cell {
                        Checkbox(checked = false, onCheckedChange = {

                        })
                    }
                    columns.map { (_, propertyName) ->
                        cell {
                            TextField(value = item::class.declaredMemberProperties.first {
                                it.name.lowercase() == propertyName.lowercase()
                            }.call(item).toString(), enabled = false, onValueChange = {

                            })
                        }
                    }
                    cell {
                        IconButton(onClick = {}) {
                            Icon(imageVector = EvaIcons.Outline.Edit, contentDescription = null)
                        }
                        IconButton(onClick = {}) {
                            Icon(imageVector = EvaIcons.Outline.Trash2, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}