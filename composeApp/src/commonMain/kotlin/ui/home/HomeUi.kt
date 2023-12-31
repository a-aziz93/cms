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
import com.seanproctor.datatable.material3.DataTable
import com.seanproctor.datatable.material3.Material3CellContentProvider
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.BasicPaginatedDataTable
import com.seanproctor.datatable.paging.PaginatedDataTableState
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.ChevronUp
import core.util.toHslColor
import java.util.stream.IntStream.range
import kotlin.reflect.full.declaredMemberProperties
import kotlin.math.min

data class TableColumn(val headerName: String, val propertyName: String)

@Composable
internal fun HomeUi(component: HomeComponent) {
    Table(
        columns = listOf(
            TableColumn("HeaderName", "headerName"),
            TableColumn("PropertyName", "propertyName"),
        ),
    ) {
        (1..10).map {
            TableColumn("Atoev$it", "Aziz$it")
        }
    }
}

@Composable
fun Table(
    modifier: Modifier = Modifier,
    separator: @Composable (rowIndex: Int) -> Unit = { Divider() },
    headerHeight: Dp = 56.dp,
    rowHeight: Dp = 52.dp,
    horizontalPadding: Dp = 16.dp,
    state: PaginatedDataTableState = rememberPaginatedDataTableState(10),
    sortColumnIndex: Int? = null,
    sortAscending: Boolean = true,
    columns: List<TableColumn>,
    items: (Int) -> List<Any>,
) {

    BasicPaginatedDataTable(
        columns = columns.map {
            IconButton(
                onClick = {}
            ) {
                Icon(imageVector = EvaIcons.Outline.ChevronUp, contentDescription = null)
            }
            DataColumn {
                Text(it.headerName)
            }
        },
        modifier = modifier,
        separator = separator,
        headerHeight = headerHeight,
        horizontalPadding = horizontalPadding,
        state = state,
        footer = {
            Row(
                modifier = Modifier.height(rowHeight).padding(horizontal = 16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val start = min(state.pageIndex * state.pageSize + 1, state.count)
                val end = min(start + state.pageSize - 1, state.count)
                val pageCount = (state.count + state.pageSize - 1) / state.pageSize
                Text("$start-$end of ${state.count}")
                IconButton(
                    onClick = { state.pageIndex = 0 },
                    enabled = state.pageIndex > 0,
                ) {
                    Icon(Icons.Default.FirstPage, "First")
                }
                IconButton(
                    onClick = { state.pageIndex-- },
                    enabled = state.pageIndex > 0,
                ) {
                    Icon(Icons.Default.ChevronLeft, "Previous")
                }
                (0..10).map {
                    Box(
                        modifier
                            .clip(shape = CircleShape)
                            .size(30.dp), contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawCircle(SolidColor(Color.Black))
                        }
                        Text(text = (state.pageIndex + it).toString(), color = Color.White)
                    }
                }
                IconButton(
                    onClick = { state.pageIndex++ },
                    enabled = state.pageIndex < pageCount - 1
                ) {
                    Icon(Icons.Default.ChevronRight, "Next")
                }
                IconButton(
                    onClick = { state.pageIndex = pageCount - 1 },
                    enabled = state.pageIndex < pageCount - 1
                ) {
                    Icon(Icons.Default.LastPage, "Last")
                }
            }
        },
        cellContentProvider = Material3CellContentProvider,
        sortColumnIndex = sortColumnIndex,
        sortAscending = sortAscending,
    ) {
        items(state.pageIndex).map { item ->
            row {
                columns.map { (_, propertyName) ->
                    cell {
                        Text(
                            item::class.declaredMemberProperties.first {
                                it.name.lowercase() == propertyName.lowercase()
                            }.call(item)
                                .toString()
                        )
                    }
                }
            }
        }
    }
}