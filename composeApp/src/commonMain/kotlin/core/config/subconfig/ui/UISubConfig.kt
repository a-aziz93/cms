package core.config.subconfig.ui

import core.config.extension.config
import nz.co.trademe.konfigure.api.ConfigRegistry
import nz.co.trademe.konfigure.SubConfig

class UISubConfig(parent: ConfigRegistry) : SubConfig(parent) {
    override val group: String
        get() = "Level 1"

    val colorSubconfig = ColorSubConfig(parent = this)
    val typoSubconfig = TypoSubConfig(parent = this)
    val shapeSubconfig = ShapeSubConfig(parent = this)
    val dimensSubConfig = DimensSubConfig(parent = this)
}