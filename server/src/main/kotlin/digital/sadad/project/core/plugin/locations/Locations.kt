package digital.sadad.project.core.plugin.locations

import io.ktor.server.application.*
import io.ktor.server.locations.*

@OptIn(KtorExperimentalLocationsAPI::class)
fun Application.configureLocations() {
    install(Locations){

    }
}