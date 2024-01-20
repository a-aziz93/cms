package digital.sadad.project.core.plugins.security

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

typealias Role = String

class RoleBasedAuthConfiguration {
    var requiredRoles: Set<String> = emptySet()
    lateinit var authType: AuthType
    var name: String = ""
}

enum class AuthType {
    ALL,
    ANY,
    NONE,
}

class AuthorizedRouteSelector(private val description: String) : RouteSelector() {
    override fun evaluate(context: RoutingResolveContext, segmentIndex: Int) = RouteSelectorEvaluation.Constant

    override fun toString(): String = "(authorize ${description})"
}

class RoleBasedAuthPluginConfiguration {
    var roleExtractor: ((Principal) -> Set<Role>) = { emptySet() }
        private set

    fun extractRoles(extractor: (Principal) -> Set<Role>) {
        roleExtractor = extractor
    }

    var throwErrorOnUnauthorizedResponse = false
}

private lateinit var pluginGlobalConfig: Map<String, RoleBasedAuthPluginConfiguration>
fun AuthenticationConfig.roleBased(name: String = "", config: RoleBasedAuthPluginConfiguration.() -> Unit) {
    pluginGlobalConfig += name to RoleBasedAuthPluginConfiguration().apply(config)
}

private fun Route.buildAuthorizedRoute(
    requiredRoles: Set<Role>,
    authType: AuthType,
    name: String,
    build: Route.() -> Unit
): Route {
    val authorizedRoute = createChild(AuthorizedRouteSelector(requiredRoles.joinToString(",")))
    authorizedRoute.install(RoleBasedAuthPlugin) {
        this.requiredRoles = requiredRoles
        this.authType = authType
        this.name = name
    }
    authorizedRoute.build()
    return authorizedRoute
}

fun Route.withRole(role: Role, name: String = "", build: Route.() -> Unit) =
    buildAuthorizedRoute(requiredRoles = setOf(role), authType = AuthType.ALL, name, build = build)

fun Route.withRoles(name: String = "", vararg roles: Role, build: Route.() -> Unit) =
    buildAuthorizedRoute(requiredRoles = roles.toSet(), authType = AuthType.ALL, name, build = build)

fun Route.withAnyRole(name: String = "", vararg roles: Role, build: Route.() -> Unit) =
    buildAuthorizedRoute(requiredRoles = roles.toSet(), authType = AuthType.ANY, name, build = build)

fun Route.withoutRoles(name: String = "", vararg roles: Role, build: Route.() -> Unit) =
    buildAuthorizedRoute(requiredRoles = roles.toSet(), authType = AuthType.NONE, name, build = build)


val RoleBasedAuthPlugin =
    createRouteScopedPlugin(name = "RoleBasedAuthorization", createConfiguration = ::RoleBasedAuthConfiguration) {
        if (::pluginGlobalConfig.isInitialized.not() || !pluginGlobalConfig.contains(pluginConfig.name)) {
            error("RoleBasedAuthPlugin not initialized. Setup plugin by calling AuthenticationConfig#roleBased in authenticate block")
        }
        with(pluginConfig) {
            on(AuthenticationChecked) { call ->
                val principal = call.principal<Principal>() ?: return@on
                val userRoles = pluginGlobalConfig[name]!!.roleExtractor.invoke(principal)
                val denyReasons = mutableListOf<String>()

                when (authType) {
                    AuthType.ALL -> {
                        val missing = requiredRoles - userRoles
                        if (missing.isNotEmpty()) {
                            denyReasons += "Principal lacks required role(s) ${missing.joinToString(" and ")}"
                        }
                    }

                    AuthType.ANY -> {
                        if (userRoles.none { it in requiredRoles }) {
                            denyReasons += "Principal has none of the sufficient role(s) ${
                                requiredRoles.joinToString(
                                    " or "
                                )
                            }"
                        }
                    }

                    AuthType.NONE -> {
                        if (userRoles.any { it in requiredRoles }) {
                            denyReasons += "Principal has forbidden role(s) ${
                                (requiredRoles.intersect(userRoles)).joinToString(
                                    " and "
                                )
                            }"

                        }
                    }
                }
                if (denyReasons.isNotEmpty()) {
                    if (pluginGlobalConfig[name]!!.throwErrorOnUnauthorizedResponse) {
                        throw UnauthorizedAccessException(denyReasons)
                    } else {
                        val message = denyReasons.joinToString(". ")
                        if (application.developmentMode) {
                            application.log.warn("Authorization failed for ${call.request.path()} $message")
                        }
                        call.respond(HttpStatusCode.Forbidden)
                    }
                }
            }
        }
    }

class UnauthorizedAccessException(val denyReasons: MutableList<String>) : Exception()
