package digital.sadad.project.core.plugins

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.plugins.model.UserSession
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import org.koin.ktor.ext.inject

fun Application.configureSession() {
    val appConfig: AppConfig by inject()
    appConfig.config.session?.let {
        install(Sessions) {
            it.userSessionCookie?.let { userSessionCookie ->
                cookie<UserSession>("user_session") {
                    userSessionCookie.maxAgeInSeconds?.let { cookie.maxAgeInSeconds = it }
                    userSessionCookie.encoding?.let { cookie.encoding = it }
                    userSessionCookie.domain?.let { cookie.domain = it }
                    userSessionCookie.path?.let { cookie.path = it }
                    userSessionCookie.secure?.let { cookie.secure = it }
                    userSessionCookie.httpOnly?.let { cookie.httpOnly = it }
                    userSessionCookie.extensions?.let { cookie.extensions + it }
                    userSessionCookie.encryption?.let { encryption ->
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
            }
        }
    }
}