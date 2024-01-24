package digital.sadad.project.core.plugin.session

import digital.sadad.project.auth.model.security.UserIdPrincipalMetadata
import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.plugin.session.SessionConfig
import io.ktor.server.application.*
import io.ktor.server.sessions.*
import java.io.File

fun Application.configureSession(appConfig: AppConfig) {
    val config = appConfig.config.session
    if (config.enable == true) {
        install(Sessions) {
            appConfig.config.security?.let {
                it.basic?.forEach { (name, config) ->
                    config.sessionCookie?.let { cookie<UserIdPrincipalMetadata>(name, it) }
                }
            }
        }
    }
}

private inline fun <reified S : Any> SessionsConfig.cookie(
    name: String,
    config: digital.sadad.project.core.config.model.plugin.session.CookieConfig,
) {
    val cookieBuilder: CookieSessionBuilder<S>.() -> Unit = {
        config.maxAgeInSeconds?.let { cookie.maxAgeInSeconds = it }
        config.encoding?.let { cookie.encoding = it }
        config.domain?.let { cookie.domain = it }
        config.path?.let { cookie.path = it }
        config.secure?.let { cookie.secure = it }
        config.httpOnly?.let { cookie.httpOnly = it }
        config.extensions?.let { cookie.extensions + it }
        config.encryption?.let { encryption ->
            transform(encryption.encryptionKey?.let {
                SessionTransportTransformerEncrypt(
                    encryptionKey = it.toByteArray(),
                    signKey = encryption.signKey.toByteArray(),
                    encryptAlgorithm = encryption.encryptAlgorithm,
                    signAlgorithm = encryption.signAlgorithm,
                )
            } ?: SessionTransportTransformerMessageAuthentication(
                encryption.signKey.toByteArray(),
                encryption.signAlgorithm
            ))
        }
    }
    (config.filePath?.let {
        cookie<S>(name, directorySessionStorage(File(it)), cookieBuilder)
    } ?: config.inMemory?.let {
        cookie<S>(
            name,
            SessionStorageMemory(),
            cookieBuilder
        )
    } ?: cookie<S>(
        name,
        cookieBuilder
    ))
}