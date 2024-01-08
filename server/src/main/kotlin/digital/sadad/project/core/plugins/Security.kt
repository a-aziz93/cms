package digital.sadad.project.core.plugins

import digital.sadad.project.auth.service.token.JWTHS256Service
import digital.sadad.project.auth.service.token.JWTRS256Service
import digital.sadad.project.core.config.AppConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject
import kotlin.collections.set

// Seguridad en base a JWT
fun Application.configureSecurity() {

    val appConfig: AppConfig by inject()

    val authConfig = appConfig.config.auth

    if (authConfig != null) {

        // Inject the token services
        val jwtHS256Service: JWTHS256Service by inject()
        val jwtRS256Service: JWTRS256Service by inject()

        authentication {
            if (authConfig.oauth != null) {
                val redirects = mutableMapOf<String, String>()
                authConfig.oauth.entries.forEach { (name, oauthConfig) ->
                    oauth(name) {
                        // Configure oauth authentication
                        urlProvider = { oauthConfig.urlProvider.redirectUrl }
                        providerLookup = {
                            OAuthServerSettings.OAuth2ServerSettings(
                                name = oauthConfig.serverProvider.name,
                                authorizeUrl = oauthConfig.serverProvider.authorizeUrl,
                                accessTokenUrl = oauthConfig.serverProvider.accessTokenUrl,
                                clientId = oauthConfig.serverProvider.clientId,
                                clientSecret = oauthConfig.serverProvider.clientSecret,
                                accessTokenRequiresBasicAuth = false,
                                requestMethod = oauthConfig.serverProvider.requestMethod,
                                defaultScopes = oauthConfig.serverProvider.defaultScopes,
                                extraAuthParameters = oauthConfig.serverProvider.extraAuthParameters,
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

                jwtHS256Service.jwts.forEach { (name, jwt) ->
                    jwt(name) {
                        // With realm, we can get the token from the request
                        realm = jwt.config.realm

                        // Load the token verification config
                        verifier(jwt.verifyJWT())

                        validate { credential ->
                            // If the token is valid, it also has the indicated audience,
                            // and has the user's field to compare it with the one we want
                            // return the JWTPrincipal, otherwise return null
                            jwt.validate(credential)
                        }

                        challenge { defaultScheme, realm ->
                            call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
                        }
                    }
                }

                jwtRS256Service.jwts.forEach { (name, jwt) ->
                    jwt(name) {
                        // With realm, we can get the token from the request
                        realm = jwt.config.realm

                        // Load the token verification config
                        verifier(jwt.jwkProvider, jwt.config.issuer) {
                            acceptLeeway(3)
                        }

                        validate { credential ->
                            // If the token is valid, it also has the indicated audience,
                            // and has the user's field to compare it with the one we want
                            // return the JWTPrincipal, otherwise return null
                            jwt.validate(credential)
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


