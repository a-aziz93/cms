package digital.sadad.project.auth.service.token

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import digital.sadad.project.auth.model.JWTHS256
import digital.sadad.project.auth.model.User
import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.JWTHS256Config
import mu.two.KotlinLogging
import org.koin.core.annotation.Single
import java.util.*
import kotlin.time.DurationUnit

private val logger = KotlinLogging.logger {}

@Single
class JWTHS256Service(
    appConfig: AppConfig,
    val jwts: Map<String, JWTHS256> = (appConfig.config.auth?.jwtHS256 ?: emptyMap()).map {
        it.key to JWTHS256(it.value)
    }.toMap()
)