package core.config

import core.config.extension.configFlow
import kotlinx.coroutines.flow.StateFlow

class AppConfig : Config(
    sources = emptyList(),
    targets = emptyList(),
) {

    val test: StateFlow<String?> by configFlow(
        key = "testString",
        defaultValue = "This is a test config value",
    )
}