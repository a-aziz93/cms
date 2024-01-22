package digital.sadad.project.core.plugins.security

import digital.sadad.project.auth.model.security.UserPrincipal
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
import io.ktor.http.*
import io.ktor.http.auth.*
import io.ktor.http.parsing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named
import org.koin.ktor.ext.inject
import java.nio.charset.Charset
import kotlin.collections.set

fun Application.configureSecurity() {
    val appConfig: AppConfig by inject()

    appConfig.config.auth?.let {

        // BASIC
        val basicAuthService: Map<String, Lazy<BasicAuthService>>? = it.basic?.keys?.associate {
            it to inject<BasicAuthService>(named(it))
        }
        // DIGEST
        val digestAuthService: DigestAuthService by inject()
        // BEARER
        val bearerAuthService: BearerAuthService by inject()
        // FORM
        val formAuthService: FormAuthService by inject()
        // SESSION
        val sessionAuthService: SessionAuthService by inject()
        // SESSION
        val ldapAuthService: LDAPAuthService by inject()
        // JWT
        val jwtHS256Service: JWTHS256Service by inject()
        val jwtRS256Service: JWTRS256Service by inject()
        //OAUTH
        val oauthService: OAuthService by inject()

        authentication {
            // BASIC
            it.basic?.forEach { (name, config) ->
                basic(name) {
                    config.realm?.let { realm = it }

                    config.charset?.let { charset = Charset.forName(it) }

                    validate {
                        basicAuthService.validate(it)
                    }

                    skipWhen {
                        basicAuthService.skip(it)
                    }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        basicAuthService.roles(it)
                    }
                }
            }

            // DIGEST
            it.digest?.forEach { (name, config) ->
                digest(name) {
                    config.realm?.let { realm = it }

                    config.algorithmName?.let { algorithmName = it }

                    validate {
                        digestAuthService.validate(it)
                    }

                    digestProvider { userName, realm ->
                        digestAuthService.provider(userName, realm)
                    }

                    skipWhen { digestAuthService.skip(it) }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        digestAuthService.roles(it)
                    }
                }
            }

            // BEARER
            it.bearer?.forEach { (name, config) ->
                bearer(name) {
                    config.realm?.let { realm = it }

                    authenticate {
                        bearerAuthService.validate(it)
                    }

                    config.authHeader?.let { header ->
                        authHeader {
                            it.authHeader(header)
                        }
                    }

                    config.authSchemes?.let { authSchemes(it.defaultScheme, *it.additionalSchemes.toTypedArray()) }

                    skipWhen { bearerAuthService.skip(it) }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        bearerAuthService.roles(it)
                    }
                }
            }

            // FORM
            it.form?.forEach { (name, config) ->
                form(name) {
                    config.userParamName?.let { userParamName = it }

                    config.passwordParamName?.let { passwordParamName = it }

                    challenge {
                        formAuthService.challenge(call)
                    }

                    validate {
                        formAuthService.validate(it)
                    }

                    skipWhen { formAuthService.skip(it) }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        formAuthService.roles(it)
                    }
                }
            }

            // SESSION
            it.session?.forEach { (name, config) ->
                session<UserPrincipal>(name) {

                    challenge {
                        sessionAuthService.challenge(call)
                    }

                    validate {
                        sessionAuthService.validate(it)
                    }

                    skipWhen { sessionAuthService.skip(it) }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        sessionAuthService.roles(it)
                    }
                }
            }

            // LDAP
            it.ldap?.forEach { (name, config) ->
                basic(name) {

                    config.realm?.let { realm = it }

                    config.charset?.let { charset = Charset.forName(it) }

                    validate {
                        ldapAuthService.validate(it, "ldap://0.0.0.0:389", "cn=%s,dc=ktor,dc=io")
                    }

                    skipWhen { ldapAuthService.skip(it) }
                }
                rbac(name) {
                    extractRoles {
                        // From UserIdPrincipal
                        ldapAuthService.roles(it)
                    }
                }
            }

            // JWTHS256
            jwtHS256Service.jwts?.forEach { (name, jwt) ->
                jwt(name) {
                    // With realm, we can get the token from the request
                    jwt.config.realm?.let { realm = it }

                    jwt.config.authHeader?.let { header ->
                        authHeader {
                            it.authHeader(header)
                        }
                    }

                    jwt.config.authSchemes?.let { authSchemes(it.defaultScheme, *it.additionalSchemes.toTypedArray()) }

                    // Load the token verification config
                    verifier(jwt.verifyJWT())

                    validate {
                        // If the token is valid, it also has the indicated audience,
                        // and has the user's field to compare it with the one we want
                        // return the JWTPrincipal, otherwise return null
                        jwt.validate(it)
                    }

                    challenge { defaultScheme, realm ->
                        call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
                    }

                    skipWhen { jwtHS256Service.skip(it) }

                }
                rbac(name) {
                    extractRoles {
                        //Extract JWTPrincipal
                        jwtHS256Service.roles(it)
                    }
                }
            }

            // JWTRS256
            jwtRS256Service.jwts?.forEach { (name, jwt) ->
                jwt(name) {
                    // With realm, we can get the token from the request
                    jwt.config.realm?.let { realm = it }

                    jwt.config.authHeader?.let { header ->
                        authHeader {
                            it.authHeader(header)
                        }
                    }

                    jwt.config.authSchemes?.let { authSchemes(it.defaultScheme, *it.additionalSchemes.toTypedArray()) }

                    // Load the token verification config
                    verifier(jwt.jwkProvider, jwt.config.issuer) {
                        acceptLeeway(3)
                    }

                    validate {
                        // If the token is valid, it also has the indicated audience,
                        // and has the user's field to compare it with the one we want
                        // return the JWTPrincipal, otherwise return null
                        jwt.validate(it)
                    }

                    challenge { defaultScheme, realm ->
                        call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
                    }

                    skipWhen { jwtRS256Service.skip(it) }

                }
                rbac(name) {
                    extractRoles {
                        //Extract JWTPrincipal
                        jwtRS256Service.roles(it)
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

                    skipWhen { oauthService.skip(it) }

                }
                rbac(name) {
                    extractRoles {
                        // From
                        oauthService.roles(it)
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

private fun ApplicationCall.authHeader(header: String) = this.request.headers[header]?.let {
    try {
        parseAuthorizationHeader(it)
    } catch (cause: ParseException) {
        throw BadRequestException("Invalid auth header", cause)
    }
}


