package core.config.subconfig.ui

import nz.co.trademe.konfigure.api.ConfigRegistry
import nz.co.trademe.konfigure.SubConfig

class TypoSubConfig(parent: ConfigRegistry): SubConfig(parent) {
    override val group: String
        get() = "Level 2"

}