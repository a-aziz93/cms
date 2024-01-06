package ui.common.component.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ui.common.component.search.model.SearchFieldState
import ui.common.component.search.model.rememberSearchFieldState
import ui.common.model.SelectableColor

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    fontSize: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Center,
    showMatchers: Boolean = true,
    state: SearchFieldState = rememberSearchFieldState(),
    matchCaseColor: SelectableColor? = null,
    matchWordColor: SelectableColor? = null,
    matchRegexColor: SelectableColor? = null,
) {
    Box(
        modifier = modifier
            .background(
                color = Color.White.copy(alpha = 0.1f)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .weight(1f),
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                    )
                },
                textStyle = LocalTextStyle.current.copy(
                    textAlign = textAlign,
                    fontSize = fontSize
                ),
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Black.copy(0.2f)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            if (showMatchers) {
                IconButton(
                    onClick = {
                        state.matchCase = !state.matchCase
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
                    },
                ) {
                    if (matchRegexColor == null || (!state.matchRegex && matchRegexColor.color == null)) Text(".*") else Text(
                        text = ".*",
                        color = if (state.matchRegex) matchRegexColor.selectedColor else matchRegexColor.color!!
                    )
                }
            }
        }
    }
}