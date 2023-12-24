package ui.i18n

import kotlin.reflect.full.memberProperties

object Locales {
    const val EN = "en"
    const val RU = "ru"
    const val TJ = "tg"
}

val supportedLocaleCodes:List<String> = Locales::class.memberProperties.map { it.call() as String }

fun String.toCountryAlpha2Code(): String {
    return when (this) {
        "en" -> "us"
        "ru" -> "ru"
        "tg" -> "tj"
        else -> ""
    }
}

fun String.toLanguageAlpha2Code(): String {
    return when (this) {
        "us" -> "en"
        "ru" -> "ru"
        "tj" -> "tg"
        else -> ""
    }
}