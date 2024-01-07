package ui.common.component.search.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class SearchFieldState(
    searchTerm: String,
    matchCase: Boolean,
    matchWord: Boolean,
    matchRegex: Boolean,
) {
    var searchTerm by mutableStateOf(searchTerm)
    var matchCase by mutableStateOf(matchCase)
    var matchWord by mutableStateOf(matchWord)
    var matchRegex by mutableStateOf(matchRegex)

    companion object {
        val Saver: Saver<SearchFieldState, *> = listSaver(
            save = { listOf(it.searchTerm, it.matchCase, it.matchWord, it.matchRegex) },
            restore = {
                SearchFieldState(it[0] as String, it[1] as Boolean, it[2] as Boolean, it[3] as Boolean)
            }
        )
    }
}

@Composable
fun rememberSearchFieldState(
    searchTerm: String = "",
    initialMatchCase: Boolean = true,
    initialMatchWord: Boolean = true,
    initialMatchRegex: Boolean = false,
): SearchFieldState {
    return rememberSaveable(saver = SearchFieldState.Saver) {
        SearchFieldState(searchTerm, initialMatchCase, initialMatchWord, initialMatchRegex)
    }
}