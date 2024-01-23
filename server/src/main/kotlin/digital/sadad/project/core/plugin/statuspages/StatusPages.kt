package digital.sadad.project.core.plugin.statuspages

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import digital.sadad.project.auth.error.token.TokenException
import digital.sadad.project.core.config.AppConfig
import org.koin.ktor.ext.inject

/**
 * Configure the Status Pages plugin and configure it
 * https://ktor.io/docs/status-pages.html
 * We use status pages to respond with expected exceptions
 */
fun Application.configureStatusPages() {
    val appConfig: AppConfig by inject()
    appConfig.config.statusPage?.let {     // Install StatusPages plugin and configure it
        install(StatusPages) {

            // This is a custom exception we use to respond with a 400 if a validation fails, Bad Request
            exception<RequestValidationException> { call, cause ->
                call.respond(HttpStatusCode.BadRequest, cause.reasons.joinToString())
            }

            // When we try to convert a string to a number and it fails we respond with a 400 Bad Request
            exception<NumberFormatException> { call, cause ->
                call.respond(HttpStatusCode.BadRequest, "${cause.message}. The input param is not a valid number")
            }

            // When try to send a bad request we respond with a 400 Bad Request
            exception<IllegalArgumentException> { call, cause ->
                call.respond(HttpStatusCode.BadRequest, "${cause.message}")
            }

            // Token is not valid or expired
            exception<TokenException.InvalidTokenException> { call, cause ->
                call.respond(HttpStatusCode.Unauthorized, cause.message.toString())
            }

            it.status?.forEach {
                status(*it.codes.toTypedArray()) { call, status ->
                    call.respondText(text = it.text, status = status)
                }
            }

            it.statusFile?.forEach {
                statusFile(*it.codes.toTypedArray(), filePattern = it.filePattern)
            }

        }
    }
}