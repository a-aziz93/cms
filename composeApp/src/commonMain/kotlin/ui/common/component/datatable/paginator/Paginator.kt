package ui.common.component.datatable.paginator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.LastPage
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.min

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