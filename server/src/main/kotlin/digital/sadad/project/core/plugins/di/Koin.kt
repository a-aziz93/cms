package digital.sadad.project.core.plugins.di

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.plugins.di.module.database.databaseModule
import digital.sadad.project.core.plugins.di.module.keycloak.keycloakModule
import digital.sadad.project.core.plugins.di.module.security.securityModule
import io.ktor.server.application.*
import org.koin.ksp.generated.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.ufoss.kotysa.*

fun Application.configureKoin() {
    val appConfig: AppConfig by inject()

    install(Koin) {
        // Logger
        slf4jLogger()

        // Default module with Annotations
        defaultModule()

        // Database module with clients
        appConfig.config.databases?.let { databaseModule(it) }

        // Security module
        appConfig.config.security?.let { securityModule(it) }

        // Keycloak module with clients
        appConfig.config.security?.oauth?.let {
            keycloakModule(it.entries.filter { it.value.serverProvider.name == "keycloak" && it.value.client != null }
                .associate { it.key to it.value.client!! })
        }
    }
}



