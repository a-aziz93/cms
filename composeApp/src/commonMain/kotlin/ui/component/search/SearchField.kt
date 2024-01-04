package ui.component.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    fontSize: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Center,
    onMatchCase: (() -> Unit)? = null,
    onMatchWord: (() -> Unit)? = null,
    onMatchRegex: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .background(
                color = Color.White.copy(alpha = 0.1f)
            )
    ) {
        Row {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = textAlign,
                    fontSize = fontSize
                ),
                singleLine = true,
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
                    Text("Cc")
                }
            }
            if (onMatchWord != null) {
                IconButton(
                    onClick = onMatchWord,
                ) {
                    Text("W")
                }
            }
            if (onMatchRegex != null) {
                IconButton(
                    onClick = onMatchRegex,
                ) {
                    Text(".*")
                }
            }
        }
        if (value.isEmpty()) {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.then(
                    Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 52.dp)
                )
            )
        }
    }
}