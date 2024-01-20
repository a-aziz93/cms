package digital.sadad.project.core.plugins.koin

import io.ktor.server.application.*
import org.koin.ksp.generated.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger() // Logger
        defaultModule() // Default module with Annotations
    }
}