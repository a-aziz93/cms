package digital.sadad.project.core.plugin.locations

import digital.sadad.project.core.config.model.plugin.validation.LocationsConfig
import io.ktor.server.application.*
import io.ktor.server.locations.*

@OptIn(KtorExperimentalLocationsAPI::class)
fun Application.configureLocations(config: LocationsConfig) {
    if (config.enable == true) {
        install(Locations) {

        }
    }
}