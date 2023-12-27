package digital.sadad.project.core.config.model

import io.ktor.http.*

//For Keycloak you can find information about authorizeUrl, accessTokenUrl, clientId and clientSecret in Keycloak Clients page and https:/<your-keycloak-url>/realms/<your-realm-name>/.well-known/openid-configuration
data class OAuthServerConfig(
    val name: String,
    val authorizeUrl: String,
    val accessTokenUrl: String,
    val clientId: String,
    val clientSecret: String,
    val requestMethod: HttpMethod = HttpMethod.Get,
    val defaultScopes: List<String> = emptyList(),
    val accessTokenRequiresBasicAuth: Boolean = false,
    val passParamsInURL: Boolean = false,
    val extraAuthParameters: List<Pair<String, String>> = emptyList(),
    val extraTokenParameters: List<Pair<String, String>> = emptyList(),
)
