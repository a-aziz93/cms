package core.config.handler

import com.russhwolf.settings.Settings
import nz.co.trademe.konfigure.api.OverrideHandler

class SettingsHandler : OverrideHandler {

    private val settings: Settings by lazy { Settings() }

    override fun set(key: String, value: String) {
        settings
            .putString(key, value)
    }

    override fun clear(key: String) {
        settings
            .remove(key)
    }

    override val all: Map<String, String>
        get() = settings.keys.associateWith { settings.getString(it, "") }
}