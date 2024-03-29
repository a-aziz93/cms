package digital.sadad.project.core.plugin.validation

import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import digital.sadad.project.auth.validator.userValidation
import digital.sadad.project.core.config.model.plugin.validation.ValidationConfig

/**
 * Configure the validation plugin
 * https://ktor.io/docs/request-validation.html
 * We extend the validation with our own rules in separate file in validators package
 * like routes
 */
fun Application.configureValidation(config: ValidationConfig) {
    if (config.enable == true) {
        install(RequestValidation) {
            userValidation() // User validation
        }
    }
}