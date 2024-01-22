package digital.sadad.project.core.plugins.di

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.plugins.di.module.database.databaseModule
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
        slf4jLogger() // Logger
        defaultModule() // Default module with Annotations
        appConfig.config.databases?.let { databaseModule(it) } // Database module with clients
        appConfig.config.security?.let { securityModule(it) } // Security module
//        appConfig.config.security?.let { keycloakModule(it) } // Keycloak module with clients
    }
}



