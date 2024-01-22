package digital.sadad.project.core.plugins.session

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.session.CookieConfig
import digital.sadad.project.auth.model.security.UserIdPrincipalMetadata
import io.ktor.server.application.*
import io.ktor.server.sessions.*
import org.koin.ktor.ext.inject
import java.io.File

fun Application.configureSession() {
    val appConfig: AppConfig by inject()
    appConfig.config.session?.let {
        install(Sessions) {
            appConfig.config.security?.let {
                it.basic?.forEach {
                    cookie<UserIdPrincipalMetadata>("user_session", it)
                }
            }
        }
    }
}

private inline fun <reified S : Any> SessionsConfig.cookie(
    name: String,
    config: CookieConfig,
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