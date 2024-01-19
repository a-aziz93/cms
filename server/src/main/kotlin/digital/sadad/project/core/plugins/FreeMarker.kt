package digital.sadad.project.core.plugins

import digital.sadad.project.core.config.AppConfig
import freemarker.cache.ClassTemplateLoader
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import org.koin.ktor.ext.inject

fun Application.configureFreeMarker() {
    val appConfig: AppConfig by inject()
    val templateConfig = appConfig.config.template

    if (templateConfig != null) {
        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(this::class.java.classLoader, templateConfig.basePath ?: "templates")
        }
    }
}