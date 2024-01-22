package digital.sadad.project.auth.service.security.oauth

import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import digital.sadad.project.core.config.model.security.oauth.OAuthProviderConfig

class OAuthService(
    val config: OAuthProviderConfig,
) : SkipableAuthService, RBACAuthService {

}