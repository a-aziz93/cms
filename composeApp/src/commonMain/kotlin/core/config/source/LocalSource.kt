package core.config.source

import nz.co.trademe.konfigure.api.ConfigSource

object LocalSource : ConfigSource {

    override val all: Map<String, String>
        get() = emptyMap()
}