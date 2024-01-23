package digital.sadad.project.core.config.model.plugin.session

import io.ktor.util.*

data class SessionEncryptConfig(
    val encryptionKey: String? = null,
    val signKey: String,
    val encryptAlgorithm: String = "AES",
    val signAlgorithm: String = "HmacSHA256",
)