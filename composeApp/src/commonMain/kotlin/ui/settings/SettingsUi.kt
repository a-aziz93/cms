package ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import core.i18n.supportedLocaleCodes
import core.i18n.toCountryAlpha2Code
import core.util.countries
import core.util.countryAlpha2CodeFlagPathMap
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.settings.component.*
import ui.common.model.Item


@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SettingsUi(component: SettingsComponent) {
    val languageState: SettingValueState<Int> = rememberMemoryIntSettingState(defaultValue = 0)
    Column(

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
                        text = { Text(country.toString()) },
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