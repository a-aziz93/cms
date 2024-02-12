package digital.sadad.project.core.plugin.session

import digital.sadad.project.core.config.model.plugin.security.SecurityConfig
import digital.sadad.project.core.config.model.plugin.session.CookieConfig
import digital.sadad.project.core.config.model.plugin.session.SessionConfig
import digital.sadad.project.core.security.session.model.UserSession
import io.ktor.server.application.*
import io.ktor.server.sessions.*
import java.io.File

fun Application.configureSession(
    config: SessionConfig,
    securityConfig: SecurityConfig?,
) {
    if (config.enable == true) {
        install(Sessions) {
            securityConfig?.let {
                it.basic?.forEach {
                    it.session?.let {
                        cookie<UserSession>(it.name,it.cookie)
                    }
                }
                it.digest?.forEach {
                    it.session?.let {
                        cookie<UserSession>(it.name,it.cookie)
                    }
                }

                it.digest?.forEach {
                    it.session?.let {
                        cookie<UserSession>(it.name,it.cookie)
                    }
                }

                it.form?.forEach {
                    it.session?.let {
                        cookie<UserSession>(it.name,it.cookie)
                    }
                }

                it.ldap?.forEach {
                    it.session?.let {
                        cookie<UserSession>(it.name,it.cookie)
                    }
                }

                it.jwtHS256?.forEach {
                    it.session?.let {
                        cookie<UserSession>(it.name,it.cookie)
                    }
                }

                it.jwtRS256?.forEach {
                    it.session?.let {
                        cookie<UserSession>(it.name,it.cookie)
                    }
                }

                it.oauth?.forEach {
                    it.session?.let {
                        cookie<UserSession>(it.name,it.cookie)
                    }
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