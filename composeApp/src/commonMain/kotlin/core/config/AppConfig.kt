package core.config

import core.config.handler.SettingsHandler
import core.config.source.LocalSource
import core.config.source.SettingsSource
import core.config.subconfig.admin.AdminSubConfig
import core.config.subconfig.ui.UISubConfig
import nz.co.trademe.konfigure.Config

class AppConfig() : Config(
    configSources = listOf(LocalSource, SettingsSource),
    overrideHandler = SettingsHandler(),
) {

    override val group: String
        get() = "Level 0"

    val uiSubConfig = UISubConfig(parent = this)

    val adminSubConfig = AdminSubConfig(parent = this)

}