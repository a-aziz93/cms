package digital.sadad.project.core.plugin.routing

import core.error.HttpError
import digital.sadad.project.auth.network.restful.roleRoutes
import digital.sadad.project.auth.network.restful.userRoutes
import digital.sadad.project.auth.model.security.UserIdPrincipalMetadata
import digital.sadad.project.core.config.model.plugin.cachingheaders.CacheControlType
import digital.sadad.project.core.config.model.plugin.cachingheaders.CacheControlType.*
import digital.sadad.project.core.config.model.plugin.routing.RoutingConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.pipeline.*
import java.io.File

/**
 * Define the routing of our application based a DSL
 * https://ktor.io/docs/routing-in-ktor.html
 * we can define our routes in separate files like routes package
 */
fun Application.configureRouting(config: RoutingConfig) {
    if (config.enable == true) {
        routing {
            config.staticRootPath?.let { staticRootFolder = File("static") }

            config.staticFiles?.let {
                staticFiles(it.remotePath, File(it.pathName), it.index) {
                    it.defaultPath?.let { default(it) }
                    it.enableAutoHeadResponse?.let {
                        if (it) {
                            enableAutoHeadResponse()
                        }
                    }
                    it.preCompressed?.let { preCompressed(*it.toTypedArray()) }
                    it.contentType?.let {
                        contentType { file ->
                            it[file.name]
                        }
                    }
                    it.cacheControl?.let {
                        cacheControl { file ->
                            (it[file.name]?.let {
                                it.map {
                                    when (it.type) {
                                        NO_CACHE -> CacheControl.NoCache(it.visibility)
                                        NO_STORE -> CacheControl.NoStore(it.visibility)
                                        MAX_AGE -> CacheControl.MaxAge(
                                            it.maxAgeSeconds,
                                            it.proxyMaxAgeSeconds,
                                            it.mustRevalidate,
                                            it.proxyRevalidate,
                                            it.visibility
                                        )
                                    }
                                }
                            } ?: emptyList())
                        }
                    }
                    it.extensions?.let {
                        exclude { file ->
                            it.fold(true) { acc, next -> acc && file.path.contains(next) }
                        }
                    }
                    it.extensions?.let { extensions(*it.toTypedArray()) }
                }
            }

            config.staticResources?.let {
                staticResources(it.remotePath, it.pathName, it.index) {
                    it.defaultPath?.let { default(it) }
                    it.preCompressed?.let { preCompressed(*it.toTypedArray()) }
                    it.enableAutoHeadResponse?.let {
                        if (it) {
                            enableAutoHeadResponse()
                        }
                    }
                    it.contentType?.let {
                        contentType { url ->
                            it[url.file]
                        }
                    }
                    it.cacheControl?.let {
                        cacheControl { url ->
                            (it[url.file]?.let {
                                it.map {
                                    when (it.type) {
                                        NO_CACHE -> CacheControl.NoCache(it.visibility)
                                        NO_STORE -> CacheControl.NoStore(it.visibility)
                                        MAX_AGE -> CacheControl.MaxAge(
                                            it.maxAgeSeconds,
                                            it.proxyMaxAgeSeconds,
                                            it.mustRevalidate,
                                            it.proxyRevalidate,
                                            it.visibility
                                        )
                                    }
                                }
                            } ?: emptyList())
                        }
                    }
                    it.extensions?.let {
                        exclude { url ->
                            it.fold(true) { acc, next -> acc && url.path.contains(next) }
                        }
                    }
                    it.extensions?.let { extensions(*it.toTypedArray()) }
                }
            }


            get("/") {
                call.respondText("Hello Reactive API REST!")
            }

            get("/logout") {
                call.sessions.clear<UserIdPrincipalMetadata>()
            }
        }
        // Add our routes
        roleRoutes() // Role routes
        userRoutes() // User routes
    }
}

suspend fun PipelineContext<Unit, ApplicationCall>.handleHttpError(
    error: HttpError
) = call.respond(HttpStatusCode.fromValue(error.statusCode), error.message)
