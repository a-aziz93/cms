package ui.i18n

import kotlin.reflect.full.memberProperties

internal data class Strings(
    val signIn: String,
    val profile: String,
    val main: String,
    val map: String,
    val dashboard: String,
    val settings: String,
) {

    operator fun get(key: String): String{
        val result = this::class.memberProperties.find { it.name == key }
        return if (result == null) key else result.call(this) as String
    }
}