package digital.sadad.project.core.plugin.security

import auth.dto.user.UserDto
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import core.koin.getAll
import digital.sadad.project.core.config.model.plugin.security.SecurityConfig
import digital.sadad.project.core.service.security.basic.BasicAuthService
import digital.sadad.project.core.service.security.bearer.BearerAuthService
import digital.sadad.project.core.service.security.digest.DigestAuthService
import digital.sadad.project.core.service.security.form.FormAuthService
import digital.sadad.project.core.service.security.jwt.JWTHS256Service
import digital.sadad.project.core.service.security.jwt.JWTRS256Service
import digital.sadad.project.core.service.security.ldap.LDAPAuthService
import digital.sadad.project.core.service.security.oauth.OAuthService
import digital.sadad.project.core.service.security.session.SessionAuthService
import io.ktor.http.auth.*
import io.ktor.http.parsing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import java.nio.charset.Charset
import java.util.*
import kotlin.collections.set

fun Application.configureSecurity(
    config: SecurityConfig,
    oauthRedirectURL: String,
) {
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
                    urlProvider = { "$oauthRedirectURL/callback" }
                    providerLookup = {
                        OAuthServerSettings.OAuth2ServerSettings(
                            name = service.config.server.name,
                            authorizeUrl = service.config.server.authorizeUrl,
                            accessTokenUrl = service.config.server.accessTokenUrl,
                            clientId = service.config.server.clientId,
                            clientSecret = service.config.server.clientSecret,
                            accessTokenRequiresBasicAuth = false,
                            requestMethod = service.config.server.requestMethod,
                            defaultScopes = service.config.server.defaultScopes,
                            extraAuthParameters = service.config.server.extraAuthParameters,
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


        // LOGIN ROUTE

        jwtHS256Services.forEach { (name, service) ->
            routing {
                authenticate(name) {
                    post("/login") {
                        val user = call.receive<UserDto>()
                        // Check username and password
                        service.create(UserDto.toEntity)
                        call.respond(hashMapOf("token" to token))
                    }
                }
            }
        }

        jwtRS256Services.forEach { (name, _) ->
            routing {
                authenticate(name) {
                    post("/login") {
                        val user = call.receive<UserDto>()
                        // Check username and password
                        service.create(UserDto.toEntity)
                        call.respond(hashMapOf("token" to token))
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
                    get("/callback") {
                        val currentPrincipal: OAuthAccessTokenResponse.OAuth2? = call.principal()
                        // redirects home if the url is not found before authorization
                        currentPrincipal?.let { principal ->
                            principal.state?.let { state ->
                                call.sessions.set(UserSession(state, principal.accessToken))
                                redirects[state]?.let { redirect ->
                                    call.respondRedirect(redirect)
                                    return@get
                                }
                            }
                        }
                        call.respondRedirect("/home")
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


