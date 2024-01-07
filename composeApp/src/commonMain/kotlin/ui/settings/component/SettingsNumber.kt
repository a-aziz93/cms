package ui.settings.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun SettingsNumber(
    icon: @Composable RowScope.() -> Unit,
    title: String,
    nextButtonLabel: String = "Next",
    state: State<String>,
    onSave: (String) -> Unit,
    inputFilter: (String) -> String, // input filter for the preference
    onCheck: (String) -> Boolean
) {

    var isDialogShown by remember {
        mutableStateOf(false)
    }

    if (isDialogShown) {
        Dialog(onDismissRequest = { isDialogShown = isDialogShown.not() }) {
            TextEditNumberDialog(title, nextButtonLabel, state, inputFilter, onSave, onCheck) {
                isDialogShown = isDialogShown.not()
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {
            isDialogShown = isDialogShown.not()
        },
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                icon()
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.value,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Start,
                    )
                }
            }
            Divider()
        }
    }
}

@Composable
private fun TextEditNumberDialog(
    title: String,
    nextButtonLabel: String,
    storedValue: State<String>,
    inputFilter: (String) -> String, // filters out not needed letters 
    onSave: (String) -> Unit,
    onCheck: (String) -> Boolean,
    onDismiss: () -> Unit
) {

    var currentInput by remember {
        mutableStateOf(TextFieldValue(storedValue.value))
    }

    var isValid by remember {
        mutableStateOf(onCheck(storedValue.value))
    }

    Surface(
        color = MaterialTheme.colorScheme.surfaceTint
    ) {

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(title)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(currentInput,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    // filters the input and removes redundant numbers
                    val filteredText = inputFilter(it.text)
                    isValid = onCheck(filteredText)
                    currentInput = TextFieldValue(filteredText)
                })
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {
                    onSave(currentInput.text)
                    onDismiss()
                }, enabled = isValid) {
                    Text(nextButtonLabel)
                }
            }
        }
    }
}