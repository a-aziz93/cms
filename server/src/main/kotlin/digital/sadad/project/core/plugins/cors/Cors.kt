package digital.sadad.project.core.plugins.cors

import digital.sadad.project.core.config.AppConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import org.koin.ktor.ext.inject

fun Application.configureCors() {
    val appConfig: AppConfig by inject()
    appConfig.config.cors?.let {
        install(CORS) {
            it.hosts?.let { it.forEach { allowHost(it.host, it.schemes, it.subDomains) } }
            it.headers?.let { it.forEach { allowHeader(it) } }
            it.methods?.let { it.forEach { allowMethod(it) } }
            it.exposedHeaders?.let { it.forEach { exposeHeader(it) } }
            it.allowCredentials?.let { allowCredentials = it }
            it.maxAgeInSeconds?.let { maxAgeInSeconds = it }
            it.allowSameOrigin?.let { allowSameOrigin = it }
            it.allowNonSimpleContentTypes?.let { allowNonSimpleContentTypes = it }

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