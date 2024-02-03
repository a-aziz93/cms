package digital.sadad.project.core.security.oauth.service

import digital.sadad.project.core.security.service.RBACAuthService
import digital.sadad.project.core.security.service.SkipableAuthService

class OAuthService(
    val config: digital.sadad.project.core.config.model.plugin.security.oauth.OAuthConfig,
) : SkipableAuthService, RBACAuthService {

}