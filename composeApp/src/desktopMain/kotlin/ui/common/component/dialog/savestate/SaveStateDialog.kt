package ui.common.component.dialog.savestate

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SaveStateDialog(
    onSaveState: () -> Unit,
    onExitApplication: () -> Unit,
    onDismiss: () -> Unit,
    ) {
    AlertDialog(
        onDismissRequest = onDismiss,
        buttons = {
            Row (
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.End,
                ) {
                TextButton(onClick = onDismiss) {
                    Text(text = "Cancel")
                }

                    TextButton(onClick = onExitApplication) {
                        Text(text = "No")
                    }

                    TextButton(
                        onClick = {
                            onSaveState()
                            onExitApplication()
                        }
                    ) {
                        Text(text = "Yes")
                    }
            }
                  },
        title = { Text(text = "CMS") },
        text = { Text(text = "Do you want to save the application's state?") },
        modifier = Modifier.width(400.dp),
        )
}