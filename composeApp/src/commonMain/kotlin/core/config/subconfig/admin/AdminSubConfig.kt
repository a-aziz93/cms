package core.config.subconfig.admin

import core.config.extension.config
import nz.co.trademe.konfigure.api.ConfigRegistry
import nz.co.trademe.konfigure.SubConfig

class AdminSubConfig(parent: ConfigRegistry): SubConfig(parent) {
    override val group: String
        get() = "Level 1"

}