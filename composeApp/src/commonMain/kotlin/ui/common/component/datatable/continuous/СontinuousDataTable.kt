package ui.common.component.datatable.continuous

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun СontinuousDataTable() {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp),
        state = state
    ) {

        // how to add more posts???
        items(100) { post ->
            Text(post.toString())
        }
    }
}