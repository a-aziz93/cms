package core.config

import kotlin.properties.ReadWriteProperty

interface ReadWriteConfigDelegate<T: Any>: ReadWriteProperty<ConfigRegistry, T?>