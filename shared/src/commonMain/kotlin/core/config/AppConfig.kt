package core.config

import core.config.extension.config
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class AppConfig : Config(
    emptyList(),
    emptyList(),
) {

    var test: StateFlow<String?> = config(
        key = "testString",
        defaultValue = "This is a test config value",
    )
}