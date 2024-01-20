package digital.sadad.project.core.plugins

import digital.sadad.project.core.config.AppConfig
import freemarker.cache.ClassTemplateLoader
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import org.koin.ktor.ext.inject

fun Application.configureFreeMarker() {
    val appConfig: AppConfig by inject()
    appConfig.config.template?.let {
        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(this::class.java.classLoader, it.basePath)
        }
    }
}