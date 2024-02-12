package core.config

import kotlin.properties.ReadWriteProperty

interface ConfigDelegate<T: Any>: ReadWriteProperty<ConfigRegistry, T>