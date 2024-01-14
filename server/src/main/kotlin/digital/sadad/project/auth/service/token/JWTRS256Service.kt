package digital.sadad.project.auth.service.token

import digital.sadad.project.auth.model.token.JWTRS256
import digital.sadad.project.core.config.AppConfig
import mu.two.KotlinLogging
import org.koin.core.annotation.Single

private val logger = KotlinLogging.logger {}

@Single
class JWTRS256Service(
    appConfig: AppConfig,
    val jwts: Map<String, JWTRS256> = (appConfig.config.auth?.jwtRS256 ?: emptyMap()).map {
        it.key to JWTRS256(it.value)
    }.toMap()
)