package digital.sadad.project.core.plugin.di

import digital.sadad.project.core.config.model.database.DatabaseConfig
import digital.sadad.project.core.config.model.plugin.routing.KoinConfig
import digital.sadad.project.core.config.model.plugin.security.SecurityConfig
import digital.sadad.project.core.plugin.di.module.database.databaseModule
import digital.sadad.project.core.plugin.di.module.security.securityModule
import io.ktor.server.application.*
import org.koin.core.logger.Level
import org.koin.ksp.generated.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.ufoss.kotysa.*

fun Application.configureKoin(
    config: KoinConfig?,
    databaseConfig: Map<String, DatabaseConfig>?,
    securityConfig: SecurityConfig?,
) {
    install(Koin) {
        // Logger
        config?.logConfig?.level?.let { slf4jLogger(Level.valueOf(it)) } ?: slf4jLogger()

        // Default module with Annotations
        defaultModule()

        // Database module with clients
        databaseConfig?.let { databaseModule(it) }

        // Security module
        securityConfig?.let { securityModule(it) }
    }
}



