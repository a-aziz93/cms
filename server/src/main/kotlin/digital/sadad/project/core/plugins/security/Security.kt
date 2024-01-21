package digital.sadad.project.core.plugins.security

import digital.sadad.project.auth.service.basic.BasicAuthService
import digital.sadad.project.auth.service.digest.DigestAuthService
import digital.sadad.project.auth.service.jwt.JWTHS256Service
import digital.sadad.project.auth.service.jwt.JWTRS256Service
import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.plugins.session.model.UserSession
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.koin.ktor.ext.inject
import java.nio.charset.Charset
import kotlin.collections.set

// Seguridad en base a JWT
fun Application.configureSecurity() {
    val appConfig: AppConfig by inject()

    appConfig.config.auth?.let {

        // BASIC
        val basicAuthService: BasicAuthService by inject()
        // BASIC
        val digestAuthService: DigestAuthService by inject()
        // JWT
        val jwtHS256Service: JWTHS256Service by inject()
        val jwtRS256Service: JWTRS256Service by inject()

        authentication {
            // BASIC
            it.basic?.forEach { (name, config) ->
                basic(name) {
                    config.realm?.let { realm = it }
                    config.charset?.let { charset = Charset.forName(it) }
                    validate { credential ->
                        basicAuthService.validate(credential)
                    }
                    skipWhen { call -> call.sessions.get<UserSession>() != null }
                }
            }

            // DIGEST
            it.digest?.forEach { (name, config) ->
                digest(name) {
                    config.realm?.let { realm = it }
                    config.algorithmName?.let { algorithmName = it }
                    digestProvider { userName, realm ->

                    }
                    validate { credential ->
                        digestAuthService.validate(credential)
                    }
                    skipWhen { call -> call.sessions.get<UserSession>() != null }
                }
                rbac(name) {
                    extractRoles { principal ->
                        //Extract roles from JWT payload
                        (principal as UserIdPrincipal).payload.claims?.get("roles")?.asList(String::class.java)?.toSet()
                            ?: emptySet()
                    }
                }
                UserIdPrincipal
            }

            // BEARER
            it.bearer?.forEach { (name, config) ->
                bearer(name) {
                }
            }

            // FORM
            it.form?.forEach { (name, config) ->
                form(name) {

                }
                UserIdPrincipal
            }

            // SESSION
            it.session?.forEach { (name, config) ->
                session(name) {

                }
            }

            // LDAP
            it.ldap?.forEach { (name, config) ->
                digest(name) {
                }
            }

            // JWTHS256
            jwtHS256Service.jwts?.forEach { (name, jwt) ->
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
                rbac(name) {
                    extractRoles { principal ->
                        //Extract roles from JWT payload
                        (principal as JWTPrincipal).payload.claims?.get("roles")?.asList(String::class.java)?.toSet()
                            ?: emptySet()
                    }
                }
            }

            // JWTRS256
            jwtRS256Service.jwts?.forEach { (name, jwt) ->
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
                rbac(name) {
                    extractRoles { principal ->
                        //Extract roles from JWT payload
                        (principal as JWTPrincipal).payload.claims?.get("roles")?.asList(String::class.java)?.toSet()
                            ?: emptySet()
                    }
                }
            }


            // OAUTH
            it.oauth?.forEach { (name, config) ->
                val redirects = mutableMapOf<String, String>()
                oauth(name) {
                    // Configure oauth authentication
                    urlProvider = { config.urlProvider.redirectUrl }
                    providerLookup = {
                        OAuthServerSettings.OAuth2ServerSettings(
                            name = config.serverProvider.name,
                            authorizeUrl = config.serverProvider.authorizeUrl,
                            accessTokenUrl = config.serverProvider.accessTokenUrl,
                            clientId = config.serverProvider.clientId,
                            clientSecret = config.serverProvider.clientSecret,
                            accessTokenRequiresBasicAuth = false,
                            requestMethod = config.serverProvider.requestMethod,
                            defaultScopes = config.serverProvider.defaultScopes,
                            extraAuthParameters = config.serverProvider.extraAuthParameters,
                            onStateCreated = { call, state ->
                                //saves new state with redirect url value
                                call.request.queryParameters["redirectUrl"]?.let {
                                    redirects[state] = it
                                }
                            }
                        )
                    }
                }
                rbac(name) {
                    extractRoles { principal ->
                        //Extract roles from JWT payload
                        (principal as JWTPrincipal).payload.claims?.get("roles")?.asList(String::class.java)?.toSet()
                            ?: emptySet()
                    }
                }
            }

        }

        jwtHS256Service.jwts?.forEach { (name, _) ->
            routing {
                authenticate(name) {
                    get("login") {
                        // Redirects to 'authorizeUrl' automatically
                    }
                }
            }
        }

        jwtRS256Service.jwts?.forEach { (name, _) ->
            routing {
                authenticate(name) {
                    get("login") {
                        // Redirects to 'authorizeUrl' automatically
                    }
                }
            }
        }


        it.oauth?.forEach { (name, _) ->
            routing {
                authenticate(name) {
                    get("login") {
                        // Redirects to 'authorizeUrl' automatically
                    }
                }
            }
        }
    }
}


