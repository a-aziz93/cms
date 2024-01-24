package digital.sadad.project.core.plugin.cors

import digital.sadad.project.core.config.model.plugin.cors.CORSConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.configureCors(config: CORSConfig) {
    if (config.enable == true) {
        install(CORS) {
            config.hosts?.let { it.forEach { allowHost(it.host, it.schemes, it.subDomains) } }
            config.headers?.let { it.forEach { allowHeader(it) } }
            config.methods?.let { it.forEach { allowMethod(it) } }
            config.exposedHeaders?.let { it.forEach { exposeHeader(it) } }
            config.allowCredentials?.let { allowCredentials = it }
            config.maxAgeInSeconds?.let { maxAgeInSeconds = it }
            config.allowSameOrigin?.let { allowSameOrigin = it }
            config.allowNonSimpleContentTypes?.let { allowNonSimpleContentTypes = it }

            // We can also specify options
            /*allowHost("client-host") // Allow requests from client-host
        allowHost("client-host:8081") // Allow requests from client-host on port 8081
        allowHost(
            "client-host",
            subDomains = listOf("en", "de", "es")
        ) // Allow requests from client-host on subdomains en, de and es
        allowHost("client-host", schemes = listOf("http", "https")) // Allow requests from client-host on http and https

        // or methods
        allowMethod(HttpMethod.Put) // Allow PUT method
        allowMethod(HttpMethod.Delete)  // Allow DELETE method*/
        }
    }
}