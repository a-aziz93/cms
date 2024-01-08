package ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.i18n.supportedLocaleCodes
import core.i18n.toCountryAlpha2Code
import core.model.countries
import core.model.countryAlpha2CodeFlagPathMap
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.settings.component.*
import ui.common.model.Item


@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SettingsUi(component: SettingsComponent) {
    val languageState: SettingValueState<Int> = rememberMemoryIntSettingState(defaultValue = 0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .width(600.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SettingsGroup(
                title = {
                    Text(
                        text = "User Interface",
                    )
                }
            ) {
                SettingsSwitch(
                    state = true,
                    title = { Text(text = "Dark mode") },
                    onCheckedChange = {},
                )
                SettingsListDropdown(
                    title = { Text("Language") },
                    items = supportedLocaleCodes.map { lng ->
                        val lngCountryAlpha2Code = lng.toCountryAlpha2Code()
                        val country = countries.find { it.alpha2Code == lngCountryAlpha2Code }!!
                        Item(
                            text = { Text(country.name) },
                            icon = {
                                Image(
                                    painter = painterResource(countryAlpha2CodeFlagPathMap[country.alpha2Code]!!),
                                    contentDescription = country.name
                                )
                            }
                        )
                    },
                    state = languageState
                )
            }
        }
    }
}

@Composable
fun rememberMemoryIntSettingState(defaultValue: Int = -1): SettingValueState<Int> {
    return remember { InMemoryIntSettingValueState(defaultValue) }
}

internal class InMemoryIntSettingValueState(private val defaultValue: Int) :
    SettingValueState<Int> {
    override var value: Int by mutableStateOf(defaultValue)
    override fun reset() {
        value = defaultValue
    }
}