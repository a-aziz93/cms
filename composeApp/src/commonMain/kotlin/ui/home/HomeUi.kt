package ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import java.util.stream.IntStream.range
import kotlin.reflect.full.declaredMemberProperties

data class TableColumn(val headerName: String, val propertyName: String)

@Composable
internal fun HomeUi(component: HomeComponent) {
    DataTable(
        columns = listOf(
            TableColumn("Header", "headername"),
            TableColumn("Property", "propertyname")
        ),
        items = (1..200).map {
            TableColumn("Atoev$it", "Aziz$it")
        }
    )
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    alignment: TextAlign = TextAlign.Center,
    title: Boolean = false
) {
    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(10.dp),
        fontWeight = if (title) FontWeight.Bold else FontWeight.Normal,
        textAlign = alignment,
    )
}

@Composable
fun RowScope.StatusCell(
    text: String,
    weight: Float,
    alignment: TextAlign = TextAlign.Center,
) {

    val color = when (text) {
        "Pending" -> Color(0xfff8deb5)
        "Paid" -> Color(0xffadf7a4)
        else -> Color(0xffffcccf)
    }
    val textColor = when (text) {
        "Pending" -> Color(0xffde7a1d)
        "Paid" -> Color(0xff00ad0e)
        else -> Color(0xffca1e17)
    }

    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(12.dp)
            .background(color, shape = RoundedCornerShape(50.dp)),
        textAlign = alignment,
        color = textColor
    )
}


@Composable
fun DataTable(
    columns: List<TableColumn>,
    items: List<Any>
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillParentMaxHeight(1f)
            .padding(8.dp),
        state = listState
    ) {
        item {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                columns.forEach { (headerName, _) ->
                    TableCell(
                        text = headerName,
                        weight = 1f / columns.size,
                        alignment = TextAlign.Left,
                        title = true
                    )
                }
            }
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
            )

            items.forEachIndexed { _, item ->
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    columns.forEach { (_, propertyName) ->

                        TableCell(
                            text = item::class.declaredMemberProperties.first {
                                it.name.lowercase() == propertyName.lowercase()
                            }.call(item)
                                .toString(),
                            weight = 1f / columns.size,
                            alignment = TextAlign.Left
                        )
                    }
                }
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }
    }
}