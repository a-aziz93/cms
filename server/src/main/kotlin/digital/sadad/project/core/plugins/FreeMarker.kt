package digital.sadad.project.core.plugins

import digital.sadad.project.core.config.AppConfig
import freemarker.cache.ClassTemplateLoader
import freemarker.cache.FileTemplateLoader
import freemarker.cache.MultiTemplateLoader
import freemarker.cache.TemplateLoader
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import org.koin.ktor.ext.inject
import java.io.File

fun Application.configureFreeMarker() {
    val appConfig: AppConfig by inject()
    appConfig.config.template?.let {
        install(FreeMarker) {
            val templateLoaders = (it.baseClassPaths?.let {
                it.map { ClassTemplateLoader(this::class.java.classLoader, it) }
            } ?: emptyList()) +
                    (it.baseFilePaths?.let {
                        it.map { FileTemplateLoader(File("./customer/changeAddress")) }
                    } ?: emptyList())

            if (templateLoaders.isNotEmpty()) {
                templateLoader =
                    MultiTemplateLoader(templateLoaders.toTypedArray())
            }
        }
    }
}