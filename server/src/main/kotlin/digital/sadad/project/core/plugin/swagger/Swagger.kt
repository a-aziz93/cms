package digital.sadad.project.core.plugin.swagger

import digital.sadad.project.core.config.model.plugin.swagger.SwaggerConfig
import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.server.application.*

fun Application.configureSwagger(config: SwaggerConfig) {
    if (config.enable == true) {
        // https://github.com/SMILEY4/ktor-swagger-ui/wiki/Configuration
        // http://xxx/swagger/
        install(SwaggerUI) {
            swagger {
                config.forwardRoot?.let { forwardRoot = it }
                config.swaggerUrl?.let { swaggerUrl = it }
                config.rootHostPath?.let { rootHostPath = it }
                config.authentication?.let { authentication = it }
            }
            config.info?.let {
                info {
                    it.title?.let { title = it }
                    it.version?.let { version = it }
                    it.description?.let { description = it }
                    it.termsOfService?.let { termsOfService = it }
                    it.contact?.let {
                        contact {
                            it.name?.let { name = it }
                            it.url?.let { url = it }
                            it.email?.let { email = it }
                        }
                    }
                    it.license?.let {
                        license {
                            it.name?.let { name = it }
                            it.url?.let { url = it }
                        }
                    }
                }
            }
            config.securityScheme?.forEach {
                // We can add security
                securityScheme(it.key) {
                    it.value.type?.let { type = it }
                    it.value.name?.let { name = it }
                    it.value.location?.let { location = it }
                    it.value.scheme?.let { scheme = it }
                    it.value.bearerFormat?.let { bearerFormat = it }
                    it.value.openIdConnectUrl?.let { openIdConnectUrl = it }
                    it.value.description?.let { description = it }
                }
            }
        }
    }
}
