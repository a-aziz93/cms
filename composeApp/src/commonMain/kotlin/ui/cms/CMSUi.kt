package ui.cms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.common.component.datatable.DiscreteDataTable
import ui.common.component.datatable.model.TableColumn

@Composable
internal fun CMSUi(component: CMSComponent) {
    DiscreteDataTable(
        modifier = Modifier.fillMaxSize(),
        title = { Text(text = "USER MANAGEMENT", style = MaterialTheme.typography.titleLarge) },
        columns = listOf(
            TableColumn("HeaderName", "headerName"),
            TableColumn("PropertyName", "propertyName"),
        ),
        pageCount = 100,
    ) {
        (1..10).map {
            TableColumn("Atoev$it", "Aziz$it")
        }
    }
}