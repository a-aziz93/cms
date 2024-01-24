package digital.sadad.project.core.plugin.dataconversion

import digital.sadad.project.core.config.model.plugin.dataconversion.DataConversionConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.dataconversion.*

fun Application.configureDataConversion(config: DataConversionConfig) {
    if (config.enable == true) {
        install(DataConversion) {

        }
    }
}