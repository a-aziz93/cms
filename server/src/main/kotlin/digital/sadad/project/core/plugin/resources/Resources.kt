package digital.sadad.project.core.plugin.resources

import digital.sadad.project.core.config.model.plugin.autoheadresponse.ResourcesConfig
import io.ktor.server.application.*
import io.ktor.server.resources.*

fun Application.configureResources(config: ResourcesConfig) {
    if (config.enable == true) {
        install(Resources) {

        }
    }
}