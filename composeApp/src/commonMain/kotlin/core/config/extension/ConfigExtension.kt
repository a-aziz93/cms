package core.config.extension

import nz.co.trademe.konfigure.api.ConfigRegistry
import nz.co.trademe.konfigure.extensions.config
import nz.co.trademe.konfigure.api.ConfigRegistrar

/**
 * Published extension function for simplifying the use of display metadata. Note, any consumer could implement similar extension
 * functions to ease use of [DisplayMetadata].
 *
 * @param group The group to assign this config item to
 * @param title The title to display in the override UI
 * @param description The description to display in the override UI
 * @param key The Config Key
 * @param defaultValue The default value of this config item
 */
inline fun <reified T: Any> ConfigRegistry.config(
    title: String? = null,
    description: String? = null,
    group: String? = null,
    key: String? = null,
    defaultValue: T? = null
): ConfigRegistrar<T> = config(
    key = key,
    defaultValue = defaultValue,
    metadataProvider = { property ->
        DisplayMetadata.from(property, this@config, group, title, description)
    }
)