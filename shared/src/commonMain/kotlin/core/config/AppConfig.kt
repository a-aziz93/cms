package core.config

import core.config.extension.config

class AppConfig : Config(
    emptyList(),
    emptyList(),
) {

    var test: String by config(
        key = "testString",
        defaultValue = "This is a test config value",
    )
}