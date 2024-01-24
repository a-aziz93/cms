package digital.sadad.project.core.plugin.templating

import digital.sadad.project.core.config.model.plugin.templating.FreeMarkerConfig
import freemarker.cache.ClassTemplateLoader
import freemarker.cache.FileTemplateLoader
import freemarker.cache.MultiTemplateLoader
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import java.io.File

fun Application.configureFreeMarker(config: FreeMarkerConfig) {
    if (config.enable == true) {
        install(FreeMarker) {
            val templateLoaders = (config.classPaths?.let {
                it.map { ClassTemplateLoader(this::class.java.classLoader, it) }
            } ?: emptyList()) +
                    (config.filePaths?.let {
                        it.map { FileTemplateLoader(File(it)) }
                    } ?: emptyList())

            if (templateLoaders.isNotEmpty()) {
                templateLoader =
                    MultiTemplateLoader(templateLoaders.toTypedArray())
            }
        }
    }
}