package digital.sadad.project.auth.service.security.oauth

import digital.sadad.project.auth.model.security.jwt.JWTHS256
import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import digital.sadad.project.core.config.AppConfig
import io.ktor.server.auth.*
import io.ktor.server.auth.ldap.*
import io.ktor.util.*
import org.koin.core.annotation.Single

@Single
class OAuthService(

) : SkipableAuthService, RBACAuthService {

}