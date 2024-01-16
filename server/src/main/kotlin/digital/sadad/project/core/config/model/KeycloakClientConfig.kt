package digital.sadad.project.core.config.model

import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder

class KeycloakClientConfig(
    @Value("\${keycloak.credentials.secret}")
    private val secretKey: String,
    @Value("\${keycloak.resource}")
    private val clientId: String,
    @Value("\${keycloak.auth-server-url}")
    private val authUrl: String,
    @Value("\${keycloak.realm}")
    private val realm: String
) {

    fun keycloak(): Keycloak {
        return KeycloakBuilder.builder()
            .grantType(CLIENT_CREDENTIALS)
            .serverUrl(authUrl)
            .realm(realm)
            .clientId(clientId)
            .clientSecret(secretKey)
            .build()
    }
}