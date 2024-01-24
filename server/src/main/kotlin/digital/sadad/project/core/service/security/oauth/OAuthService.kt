package digital.sadad.project.core.service.security.oauth

import digital.sadad.project.core.service.security.RBACAuthService
import digital.sadad.project.core.service.security.SkipableAuthService

class OAuthService(
    val config: digital.sadad.project.core.config.model.plugin.security.oauth.OAuthConfig,
) : SkipableAuthService, RBACAuthService {

}