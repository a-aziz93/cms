package ui.common.component.datatable.continuous

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun СontinuousDataTable() {
    val page = remember { mutableStateOf(1) }
    val loading = remember { mutableStateOf(false) }
    val itemList = remember { mutableStateListOf<String>() }
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(vertical = 4.dp),
        state = state
    ) {

        items(itemList) { item ->
            Text(text = item, modifier = Modifier.padding(10.dp))
        }

        item {
            if (loading.value) {
                Box(modifier = Modifier.fillMaxWidth().padding(10.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp), strokeWidth = 2.dp)
                }
            }
        }
    }

    LaunchedEffect(key1 = page.value) {
        loading.value = true
        delay(2000) // Simulate a network delay
        itemList.addAll(generateFakeData(page.value))
        loading.value = false
    }

    // Observe scroll state to load more items
    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest { index ->
                if (!loading.value && index != null && index >= itemList.size - 5) {
                    page.value++
                }
            }
    }
}

fun generateFakeData(page: Int): List<String> {
    return List(20) { "Item ${(page - 1) * 20 + it + 1}" }
}