package digital.sadad.project.core.plugin.di

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.plugin.di.module.database.databaseModule
import digital.sadad.project.core.plugin.di.module.keycloak.keycloakModule
import digital.sadad.project.core.plugin.di.module.security.securityModule
import io.ktor.server.application.*
import org.koin.core.logger.Level
import org.koin.ksp.generated.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.ufoss.kotysa.*

fun Application.configureKoin(appConfig: AppConfig) {
    val koinConfig = appConfig.config.koin
    install(Koin) {
        // Logger
        slf4jLogger(koinConfig?.logConfig?.level?.let { Level.valueOf(it) } ?: Level.INFO)

        // Default module with Annotations
        defaultModule()

        // Database module with clients
        appConfig.config.database?.let { databaseModule(it) }

        // Security module
        appConfig.config.security?.let { securityModule(it) }

        // Keycloak module with clients
        appConfig.config.security?.oauth?.let {
            keycloakModule(it.entries.filter { it.value.provider.name == "keycloak" && it.value.client != null }
                .associate { it.key to it.value.client!! })
        }
    }
}



