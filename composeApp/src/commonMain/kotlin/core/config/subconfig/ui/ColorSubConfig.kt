package core.config.subconfig.ui

import core.config.extension.config
import nz.co.trademe.konfigure.api.ConfigRegistry
import nz.co.trademe.konfigure.SubConfig

class ColorSubConfig(parent: ConfigRegistry): SubConfig(parent) {
    override val group: String
        get() = "Level 2"

}