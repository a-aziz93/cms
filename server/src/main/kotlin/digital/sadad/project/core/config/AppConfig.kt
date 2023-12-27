package digital.sadad.project.core.config

import com.sksamuel.hoplite.ConfigLoader
import digital.sadad.project.core.config.model.Config
import io.ktor.server.config.*
import org.koin.core.annotation.Singleton

/**
 * Application Configuration to encapsulate our configuration
 * from application.conf or from other sources
 */
@Singleton
class AppConfig {
    private val appConfig: ApplicationConfig = ApplicationConfig("application.yml")

    val environment = appConfig.propertyOrNull("ktor.environment") ?: ""

    val config = ConfigLoader().loadConfigOrThrow<Config>(
        // Common configuration
        "application.yml" +
                // Common profiles configuration
                (appConfig.propertyOrNull("profiles")?.getList() ?: emptyList()).map {
                    "/application-$it.yml"
                } +
                // Environment profiles configuration
                (appConfig.propertyOrNull("profiles")?.getList() ?: emptyList()).map {
                    "/application-$it-$environment.yml"
                }
    )

    // We can set here all the configuration we want from application.conf or from other sources
    // val applicationName: String = appConfig.property("ktor.application.name").getString()
    // val applicationPort: Int = appConfig.property("ktor.application.port").getString().toInt()

}