package digital.sadad.project.core.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import digital.sadad.project.auth.validator.userValidation

/**
 * Configure the validation plugin
 * https://ktor.io/docs/request-validation.html
 * We extend the validation with our own rules in separate file in validators package
 * like routes
 */
fun Application.configureValidation() {
    install(RequestValidation) {
        userValidation() // User validation
    }
}