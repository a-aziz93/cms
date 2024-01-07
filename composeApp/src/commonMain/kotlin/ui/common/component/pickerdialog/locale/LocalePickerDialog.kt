package ui.common.component.pickerdialog.locale

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.model.Country
import core.model.countries
import core.model.countryAlpha2CodeFlagPathMap
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.common.component.pickerdialog.PickerDialog
import ui.common.component.pickerdialog.model.PickerItem

@Composable
fun LocalePickerDialog(
    items: List<Country> = countries,
    selectedItem: Country = items.first(),
    onItemClick: (Country) -> Unit,
    search: Boolean = true,
    searchHint: String = "Search ...",
    rounded: Int = 12,
    onDismissRequest: () -> Unit,
) = PickerDialog(
    items = items.map {
        countryToPickerItem(it)
    },
    selectedItem = countryToPickerItem(selectedItem),
    onItemClick = {
        onItemClick(it.value!!)
    },
    searchHint = searchHint,
    rounded = rounded,
    onDismissRequest = onDismissRequest,
)

@OptIn(ExperimentalResourceApi::class)
private fun countryToPickerItem(country: Country) = PickerItem(
    { Text(modifier = Modifier.padding(horizontal = 18.dp), text = country.name) },
    {
        Image(
            painter = painterResource(countryAlpha2CodeFlagPathMap[country.alpha2Code]!!),
            contentDescription = null
        )
    }, null, country
)