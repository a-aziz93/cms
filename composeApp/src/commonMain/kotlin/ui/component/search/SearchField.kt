package ui.component.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.model.SelectableColor

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    fontSize: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Center,
    matchCase: Boolean = true,
    matchCaseColor: SelectableColor? = null,
    onMatchCase: (() -> Unit)? = null,
    matchWord: Boolean = true,
    matchWordColor: SelectableColor? = null,
    onMatchWord: (() -> Unit)? = null,
    matchRegex: Boolean = true,
    matchRegexColor: SelectableColor? = null,
    onMatchRegex: (() -> Unit)? = null,
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
            if (onMatchCase != null) {
                IconButton(
                    onClick = onMatchCase,
                ) {
                    if (matchCaseColor == null || (!matchCase && matchCaseColor.color == null)) Text("Cc") else Text(
                        text = "Cc",
                        color = if (matchCase) matchCaseColor.selectedColor else matchCaseColor.color!!
                    )
                }
            }
            if (onMatchWord != null) {
                IconButton(
                    onClick = onMatchWord,
                ) {
                    if (matchWordColor == null || (!matchWord && matchWordColor.color == null)) Text("W") else Text(
                        text = "W",
                        color = if (matchWord) matchWordColor.selectedColor else matchWordColor.color!!
                    )
                }
            }
            if (onMatchRegex != null) {
                IconButton(
                    onClick = onMatchRegex,
                ) {
                    if (matchRegexColor == null || (!matchRegex && matchRegexColor.color == null)) Text(".*") else Text(
                        text = ".*",
                        color = if (matchRegex) matchRegexColor.selectedColor else matchRegexColor.color!!
                    )
                }
            }
        }
    }
}