package digital.sadad.project.core.plugin.di.module.keycloak

import digital.sadad.project.core.config.model.plugin.security.oauth.OAuthClientConfig
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun keycloakModule(config: Map<String, OAuthClientConfig>) = module {
    config
        .forEach { (name, config) ->
            val clientBuilder = KeycloakBuilder.builder()
                .grantType(config.grantType)
                .serverUrl(config.serverUrl)
                .realm(config.realm)
                .clientId(config.clientId)
                .clientSecret(config.clientSecret)

            single<Keycloak>(named(name)) {
                when (config.grantType) {
                    OAuth2Constants.CLIENT_CREDENTIALS ->
                        clientBuilder
                            .build()

                    OAuth2Constants.PASSWORD ->
                        clientBuilder
                            .username(config.username)
                            .password(config.password)
                            .build()

                    else -> throw IllegalArgumentException("Incorrect grant type")
                }
            }
        }
}
