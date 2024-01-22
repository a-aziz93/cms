package digital.sadad.project.auth.service.security.jwt

import digital.sadad.project.auth.model.security.jwt.JWTHS256
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import digital.sadad.project.core.config.AppConfig
import mu.two.KotlinLogging
import org.koin.core.annotation.Single

private val logger = KotlinLogging.logger {}

@Single
class JWTHS256Service(
    appConfig: AppConfig,
    val jwts: Map<String, JWTHS256>? = appConfig.config.auth?.jwtHS256?.entries?.associate {
        it.key to JWTHS256(it.value)
    }
): JWTAuthService