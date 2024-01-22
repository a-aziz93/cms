package digital.sadad.project.auth.service.security.oauth

import digital.sadad.project.auth.service.security.RBACAuthService
import digital.sadad.project.auth.service.security.SkipableAuthService
import org.koin.core.annotation.Single

@Single
class OAuthService(

) : SkipableAuthService, RBACAuthService {

}