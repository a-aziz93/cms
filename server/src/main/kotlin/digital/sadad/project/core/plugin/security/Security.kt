package digital.sadad.project.core.plugin.security

import core.koin.getAll
import digital.sadad.project.auth.model.security.UserIdPrincipalMetadata
import digital.sadad.project.auth.service.security.basic.BasicAuthService
import digital.sadad.project.auth.service.security.bearer.BearerAuthService
import digital.sadad.project.auth.service.security.digest.DigestAuthService
import digital.sadad.project.auth.service.security.form.FormAuthService
import digital.sadad.project.auth.service.security.jwt.JWTHS256Service
import digital.sadad.project.auth.service.security.jwt.JWTRS256Service
import digital.sadad.project.auth.service.security.ldap.LDAPAuthService
import digital.sadad.project.auth.service.security.oauth.OAuthService
import digital.sadad.project.auth.service.security.session.SessionAuthService
import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.plugin.security.SecurityConfig
import io.ktor.http.auth.*
import io.ktor.http.parsing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.nio.charset.Charset
import kotlin.collections.set

fun Application.configureSecurity(config: SecurityConfig) {
    if (config.enable == true) {

        // BASIC
        val basicAuthServices = getAll<BasicAuthService>()
        // DIGEST
        val digestAuthServices = getAll<DigestAuthService>()
        // BEARER
        val bearerAuthServices = getAll<BearerAuthService>()
        // FORM
        val formAuthServices = getAll<FormAuthService>()
        // SESSION
        val sessionAuthServices = getAll<SessionAuthService>()
        // SESSION
        val ldapAuthServices = getAll<LDAPAuthService>()
        // JWTHS256
        val jwtHS256Services = getAll<JWTHS256Service>()
        // JWTRS256
        val jwtRS256Services = getAll<JWTRS256Service>()
        //OAUTH
        val oauthServices = getAll<OAuthService>()

        authentication {
            // BASIC
            basicAuthServices.forEach { (name, service) ->
                basic(name) {
                    service.config.realm?.let { realm = it }

                    service.config.charset?.let { charset = Charset.forName(it) }

                    validate {
                        service.validate(it)
                    }

                    skipWhen {
                        service.skip(it)
                    }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        service.roles(it)
                    }
                }
            }

            // DIGEST
            digestAuthServices.forEach { (name, service) ->
                digest(name) {
                    service.config.realm?.let { realm = it }

                    service.config.algorithmName?.let { algorithmName = it }

                    validate {
                        service.validate(it)
                    }

                    digestProvider { userName, realm ->
                        service.provider(userName, realm)
                    }

                    skipWhen { service.skip(it) }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        service.roles(it)
                    }
                }
            }

            // BEARER
            bearerAuthServices.forEach { (name, service) ->
                bearer(name) {
                    service.config.realm?.let { realm = it }

                    authenticate {
                        service.validate(it)
                    }

                    service.config.authHeader?.let { header ->
                        authHeader {
                            it.authHeader(header)
                        }
                    }

                    service.config.authSchemes?.let {
                        authSchemes(
                            it.defaultScheme,
                            *it.additionalSchemes.toTypedArray()
                        )
                    }

                    skipWhen { service.skip(it) }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        service.roles(it)
                    }
                }
            }

            // FORM
            formAuthServices.forEach { (name, service) ->
                form(name) {
                    service.config.userParamName?.let { userParamName = it }

                    service.config.passwordParamName?.let { passwordParamName = it }

                    challenge {
                        service.challenge(call)
                    }

                    validate {
                        service.validate(it)
                    }

                    skipWhen { service.skip(it) }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        service.roles(it)
                    }
                }
            }

            // SESSION
            sessionAuthServices.forEach { (name, service) ->
                session<UserIdPrincipalMetadata>(name) {

                    challenge {
                        service.challenge(call)
                    }

                    validate {
                        service.validate(it)
                    }

                    skipWhen { service.skip(it) }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        service.roles(it)
                    }
                }
            }

            // LDAP
            ldapAuthServices.forEach { (name, service) ->
                basic(name) {

                    service.config.realm?.let { realm = it }

                    service.config.charset?.let { charset = Charset.forName(it) }

                    validate {
                        service.validate(it, service.config.ldapServerURL, service.config.userDNFormat)
                    }

                    skipWhen { service.skip(it) }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        service.roles(it)
                    }
                }
            }

            // JWTHS256
            jwtHS256Services.forEach { (name, service) ->
                jwt(name) {
                    // With realm, we can get the token from the request
                    service.config.realm?.let { realm = it }

                    service.config.authHeader?.let { header ->
                        authHeader {
                            it.authHeader(header)
                        }
                    }

                    service.config.authSchemes?.let {
                        authSchemes(
                            it.defaultScheme,
                            *it.additionalSchemes.toTypedArray()
                        )
                    }

                    // Load the token verification config
                    verifier(service.verify())

                    validate {
                        // If the token is valid, it also has the indicated audience,
                        // and has the user's field to compare it with the one we want
                        // return the JWTPrincipal, otherwise return null
                        service.validate(it)
                    }

                    challenge { defaultScheme, realm ->
                        service.challenge(call, defaultScheme, realm)
                    }

                    skipWhen { service.skip(it) }

                }
                rbac(name) {
                    extractRoles {
                        //Extract JWTPrincipal
                        service.roles(it)
                    }
                }
            }

            // JWTRS256
            jwtRS256Services.forEach { (name, service) ->
                jwt(name) {
                    // With realm, we can get the token from the request
                    service.config.realm?.let { realm = it }

                    service.config.authHeader?.let { header ->
                        authHeader {
                            it.authHeader(header)
                        }
                    }

                    service.config.authSchemes?.let {
                        authSchemes(
                            it.defaultScheme,
                            *it.additionalSchemes.toTypedArray()
                        )
                    }

                    // Load the token verification config
                    verifier(service.jwkProvider, service.config.issuer) {
                        acceptLeeway(3)
                    }

                    validate {
                        // If the token is valid, it also has the indicated audience,
                        // and has the user's field to compare it with the one we want
                        // return the JWTPrincipal, otherwise return null
                        service.validate(it)
                    }

                    challenge { defaultScheme, realm ->
                        service.challenge(call, defaultScheme, realm)
                    }

                    skipWhen { service.skip(it) }

                }
                rbac(name) {
                    extractRoles {
                        //Extract JWTPrincipal
                        service.roles(it)
                    }
                }
            }


            // OAUTH
            oauthServices.forEach { (name, service) ->
                val redirects = mutableMapOf<String, String>()
                oauth(name) {
                    // Configure oauth authentication
                    urlProvider = { service.config.urlProvider.redirectUrl }
                    providerLookup = {
                        OAuthServerSettings.OAuth2ServerSettings(
                            name = service.config.serverProvider.name,
                            authorizeUrl = service.config.serverProvider.authorizeUrl,
                            accessTokenUrl = service.config.serverProvider.accessTokenUrl,
                            clientId = service.config.serverProvider.clientId,
                            clientSecret = service.config.serverProvider.clientSecret,
                            accessTokenRequiresBasicAuth = false,
                            requestMethod = service.config.serverProvider.requestMethod,
                            defaultScopes = service.config.serverProvider.defaultScopes,
                            extraAuthParameters = service.config.serverProvider.extraAuthParameters,
                            onStateCreated = { call, state ->
                                //saves new state with redirect url value
                                call.request.queryParameters["redirectUrl"]?.let {
                                    redirects[state] = it
                                }
                            }
                        )
                    }

                    skipWhen { service.skip(it) }

                }
                rbac(name) {
                    extractRoles {
                        // From
                        service.roles(it)
                    }
                }
            }
        }

        jwtHS256Services.forEach { (name, _) ->
            routing {
                authenticate(name) {
                    get("/login") {
                        // Redirects to 'authorizeUrl' automatically
                    }
                }
            }
        }

        jwtRS256Services.forEach { (name, _) ->
            routing {
                authenticate(name) {
                    get("/login") {
                        // Redirects to 'authorizeUrl' automatically
                    }
                }
            }
        }

        oauthServices.forEach { (name, _) ->
            routing {
                authenticate(name) {
                    get("/login") {
                        // Redirects to 'authorizeUrl' automatically
                    }
                }
            }
        }
    }
}

private fun ApplicationCall.authHeader(header: String) = this.request.headers[header]?.let {
    try {
        parseAuthorizationHeader(it)
    } catch (cause: ParseException) {
        throw BadRequestException("Invalid auth header", cause)
    }
}


