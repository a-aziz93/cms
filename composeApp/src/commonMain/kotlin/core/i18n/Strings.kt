package core.i18n

import kotlin.reflect.full.memberProperties

internal data class Strings(
    val signUp: String,
    val signIn: String,
    val profile: String,
    val home: String,
    val map: String,
    val cms: String,
    val queue: String,
    val cdox: String,
    val cdex: String,
    val dashboard: String,
    val settings: String,
    val serchHint: String,
) {

    operator fun get(key: String): String{
        val result = this::class.memberProperties.find { it.name == key }
        return if (result == null) key else result.call(this) as String
    }
}