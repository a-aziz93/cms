package digital.sadad.project.core.keycloak.service

import org.keycloak.OAuth2Constants.*
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder

class KeycloakService(
    grantType: String,
    serverUrl: String,
    realm: String,
    clientId: String,
    clientSecret: String,
    username: String? = null,
    password: String? = null,
) {
    val client: Keycloak by lazy {
        val clientBuilder = KeycloakBuilder.builder()
            .grantType(grantType)
            .serverUrl(serverUrl)
            .realm(realm)
            .clientId(clientId)
            .clientSecret(clientSecret)

        when (grantType) {
            CLIENT_CREDENTIALS ->
                clientBuilder
                    .build()

            PASSWORD ->
                clientBuilder
                    .username(username)
                    .password(password)
                    .build()

            else -> throw IllegalArgumentException("Incorrect grant type")
        }
    }
}