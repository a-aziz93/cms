package ui.common.component.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ui.common.component.search.model.SearchFieldState
import ui.common.component.search.model.rememberSearchFieldState
import ui.common.model.SelectableColor

@Composable
fun SearchField(
    state: SearchFieldState = rememberSearchFieldState(),
    modifier: Modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth(),
    fontSize: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Center,
    hint: String = "",
    singleLine: Boolean = false,
    onRefreshList: ((String) -> Unit)? = null,
    showMatchers: Boolean = true,
    matchCaseColor: SelectableColor? = null,
    matchWordColor: SelectableColor? = null,
    matchRegexColor: SelectableColor? = null,
    onMatcher: (((String, String) -> Boolean) -> Unit)? = null
) = TextField(
    modifier = modifier,
    textStyle = LocalTextStyle.current.copy(
        textAlign = textAlign,
        fontSize = fontSize
    ),
    value = state.searchTerm,
    onValueChange = { state.searchTerm = it },
    placeholder = {
        Text(
            text = hint,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
        )
    },
    leadingIcon = {
        Icon(
            Icons.Default.Search,
            contentDescription = null,
            tint = Color.Black.copy(0.2f)
        )
    },
    trailingIcon = {
        if (showMatchers) {
            Row {
                IconButton(
                    onClick = {
                        state.matchCase = !state.matchCase
                        state.matchRegex = false
                        onMatcher?.invoke(stringMatcher(state.matchCase, state.matchWord, state.matchRegex))
                    },
                ) {
                    if (matchCaseColor == null || (!state.matchCase && matchCaseColor.color == null)) Text("Cc") else Text(
                        text = "Cc",
                        color = if (state.matchCase) matchCaseColor.selectedColor else matchCaseColor.color!!
                    )
                }
                IconButton(
                    onClick = {
                        state.matchWord = !state.matchWord
                        state.matchRegex = false
                        onMatcher?.invoke(stringMatcher(state.matchCase, state.matchWord, state.matchRegex))
                    },
                ) {
                    if (matchWordColor == null || (!state.matchWord && matchWordColor.color == null)) Text("W") else Text(
                        text = "W",
                        color = if (state.matchWord) matchWordColor.selectedColor else matchWordColor.color!!
                    )
                }
                IconButton(
                    onClick = {
                        state.matchRegex = !state.matchRegex
                        state.matchCase = false
                        state.matchWord = false
                        onMatcher?.invoke(stringMatcher(state.matchCase, state.matchWord, state.matchRegex))
                    },
                ) {
                    if (matchRegexColor == null || (!state.matchRegex && matchRegexColor.color == null)) Text(".*") else Text(
                        text = ".*",
                        color = if (state.matchRegex) matchRegexColor.selectedColor else matchRegexColor.color!!
                    )
                }
            }
        }
    },
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions = KeyboardActions(
        onSearch = {
            onRefreshList?.invoke(state.searchTerm)
        },
    ),
    singleLine = singleLine,
    colors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )
)

private fun stringMatcher(
    matchCase: Boolean = true,
    matchWord: Boolean = true,
    matchRegex: Boolean = false,
): (String, String) -> Boolean =
    if (matchRegex) {
        { str, pattern -> Regex(pattern).matches(str) }
    } else if (matchWord) { str1, str2 -> str1.equals(str2, matchCase) } else { str1, str2 ->
        str2.contains(
            str1,
            matchCase
        )
    }