package ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.LastPage
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.DataTableScope
import com.seanproctor.datatable.TableColumnWidth
import com.seanproctor.datatable.material3.DataTable
import com.seanproctor.datatable.material3.Material3CellContentProvider
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.BasicPaginatedDataTable
import com.seanproctor.datatable.paging.PaginatedDataTableState
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.*
import core.util.toHslColor
import java.util.stream.IntStream.range
import kotlin.reflect.full.declaredMemberProperties
import kotlin.math.min

data class TableColumn(val headerName: String, val propertyName: String)

@Composable
internal fun HomeUi(component: HomeComponent) {
    Table(
        modifier = Modifier.fillMaxSize(),
        title = { Text(text = "USER MANAGEMENT", style = MaterialTheme.typography.titleLarge) },
        columns = listOf(
            TableColumn("HeaderName", "headerName"),
            TableColumn("PropertyName", "propertyName"),
        ),
        pageCount = 100,
    ) {
        (1..10).map {
            TableColumn("Atoev$it", "Aziz$it")
        }
    }
}

@Composable
fun Paginator(
    modifier: Modifier,
    pageCount: Int,
    pageSize: Int,
    pageIndex: Int,
    maxPageCount: Int,
    onFirstPageClick: () -> Unit,
    onPrevPageClick: () -> Unit,
    onPageClick: (Int) -> Unit,
    onNextPageClick: () -> Unit,
    onLastPageClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.End),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val start = min(pageIndex * pageSize + 1, pageCount)
        val end = min(start + pageSize - 1, pageCount)
        val pageCount = (pageCount + pageSize - 1) / pageSize
        Text("$start-$end of $pageCount")
        IconButton(
            onClick = onFirstPageClick,
            enabled = pageIndex > 0,
        ) {
            Icon(Icons.Default.FirstPage, "First")
        }
        IconButton(
            onClick = onPrevPageClick,
            enabled = pageIndex > 0,
        ) {
            Icon(Icons.Default.ChevronLeft, "Previous")
        }
        (0..<min(maxPageCount, pageCount)).map {
            val pi = pageIndex + it
            OutlinedIconButton(
                onClick = { onPageClick(pi) }
            ) {
                Text(pi.toString())
            }
        }
        IconButton(
            onClick = onNextPageClick,
            enabled = pageIndex < pageCount - 1
        ) {
            Icon(Icons.Default.ChevronRight, "Next")
        }
        IconButton(
            onClick = onLastPageClick,
            enabled = pageIndex < pageCount - 1
        ) {
            Icon(Icons.Default.LastPage, "Last")
        }
    }
}

@Composable
fun Table(
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