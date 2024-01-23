package digital.sadad.project.core.config.model.plugin.session

import io.ktor.http.*
import io.ktor.server.sessions.*

data class CookieConfig(
    val inMemory: Boolean? = null,
    val filePath: String? = null,
    val maxAgeInSeconds: Long? = null,
    val encoding: CookieEncoding? = null,
    val domain: String? = null,
    val path: String? = null,
    val secure: Boolean? = null,
    val httpOnly: Boolean? = null,
    val extensions: MutableMap<String, String?>? = null,
    val encryption: digital.sadad.project.core.config.model.plugin.session.SessionEncryptConfig? = null,
)
