package digital.sadad.project.core.plugins

import digital.sadad.project.auth.service.token.TokensService
import digital.sadad.project.core.config.AppConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject
import kotlin.collections.forEach
import kotlin.collections.mutableMapOf
import kotlin.collections.set

// Seguridad en base a JWT
fun Application.configureSecurity() {

    // Inject the AppConfig
    val appConfig: AppConfig by inject()
    val authConfig = appConfig.config.auth
    val jwtConfig = authConfig?.jwt

    // Inject the token service
    val jwtService: TokensService by inject()

    if (authConfig != null) {
        authentication {
            if (authConfig.oauth != null) {
                val redirects = mutableMapOf<String, String>()
                authConfig.oauth.entries.forEach { conf ->
                    oauth(conf.key) {
                        // Configure oauth authentication
                        urlProvider = { conf.value.urlProvider.redirectUrl }
                        providerLookup = {
                            OAuthServerSettings.OAuth2ServerSettings(
                                name = conf.value.serverProvider.name,
                                authorizeUrl = conf.value.serverProvider.authorizeUrl,
                                accessTokenUrl = conf.value.serverProvider.accessTokenUrl,
                                clientId = conf.value.serverProvider.clientId,
                                clientSecret = conf.value.serverProvider.clientSecret,
                                accessTokenRequiresBasicAuth = false,
                                requestMethod = conf.value.serverProvider.requestMethod,
                                defaultScopes = conf.value.serverProvider.defaultScopes,
                                extraAuthParameters = conf.value.serverProvider.extraAuthParameters,
                                onStateCreated = { call, state ->
                                    //saves new state with redirect url value
                                    call.request.queryParameters["redirectUrl"]?.let {
                                        redirects[state] = it
                                    }
                                }
                            )
                        }
                    }
                }
                if (jwtConfig != null) {
                    jwt {
                        // With realm we can get the token from the request
                        realm = jwtConfig.realm

                        // Load the token verification config
                        verifier(jwtService.verifyJWT())

                        validate { credential ->
                            // If the token is valid, it also has the indicated audience,
                            // and has the user's field to compare it with the one we want
                            // return the JWTPrincipal, otherwise return null
                            if (credential.payload.audience.contains(jwtConfig.audience) &&
                                credential.payload.getClaim("username").asString().isNotEmpty()
                            )
                                JWTPrincipal(credential.payload)
                            else null
                        }

                        challenge { defaultScheme, realm ->
                            call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
                        }
                    }
                }
            }
        }
    }
}


