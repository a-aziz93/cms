package digital.sadad.project.auth.service.token

import digital.sadad.project.auth.model.token.JWTHS256
import digital.sadad.project.core.config.AppConfig
import mu.two.KotlinLogging
import org.koin.core.annotation.Single

private val logger = KotlinLogging.logger {}

@Single
class JWTHS256Service(
    appConfig: AppConfig,
    val jwts: Map<String, JWTHS256> = (appConfig.config.auth?.jwtHS256 ?: emptyMap()).map {
        it.key to JWTHS256(it.value)
    }.toMap()
)