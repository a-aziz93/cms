package digital.sadad.project.auth.service.jwt

import digital.sadad.project.auth.model.jwt.JWTRS256
import digital.sadad.project.core.config.AppConfig
import mu.two.KotlinLogging
import org.koin.core.annotation.Single

private val logger = KotlinLogging.logger {}

@Single
class JWTRS256Service(
    appConfig: AppConfig,
    val jwts: Map<String, JWTRS256>? = appConfig.config.auth?.jwtRS256?.entries?.associate {
        it.key to JWTRS256(it.value)
    }
)