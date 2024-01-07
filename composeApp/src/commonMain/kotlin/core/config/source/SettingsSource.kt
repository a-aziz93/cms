package core.config.source

import com.russhwolf.settings.Settings
import nz.co.trademe.konfigure.api.ConfigSource

object SettingsSource : ConfigSource {

    private val settings: Settings by lazy { Settings() }

    override val all: Map<String, String>
        get() = settings.keys.associateWith { settings.getString(it, "") }
}