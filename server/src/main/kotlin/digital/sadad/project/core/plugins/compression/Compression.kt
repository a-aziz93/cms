package digital.sadad.project.core.plugins.compression

import digital.sadad.project.core.config.AppConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import org.koin.ktor.ext.inject


fun Application.configureCompression() {
    val appConfig: AppConfig by inject()
    appConfig.config.compression?.let {
        // We can configure compression here
        install(Compression) {
            //GZIP
            it.gzip?.let {
                gzip {
                    it.priority?.let {
                        priority = it
                    }
                    it.minimumSize?.let {
                        minimumSize(512)
                    }
                    it.matchContentType?.let {
                        matchContentType(
                            *it.toTypedArray()
                        )
                    }
                    it.excludeContentType?.let {
                        excludeContentType(
                            *it.toTypedArray()
                        )
                    }
                }
            }

            // DEFLATE
            it.deflate?.let {
                deflate {
                    it.priority?.let {
                        priority = it
                    }
                    it.minimumSize?.let {
                        minimumSize(512)
                    }
                    it.matchContentType?.let {
                        matchContentType(
                            *it.toTypedArray()
                        )
                    }
                    it.excludeContentType?.let {
                        excludeContentType(
                            *it.toTypedArray()
                        )
                    }
                }
            }

            // IDENTITY
            it.identity?.let {
                identity {
                    // The minimum size of a response that will be compressed
                    it.priority?.let {
                        priority = it
                    }
                    it.minimumSize?.let {
                        minimumSize(512)
                    }
                    it.matchContentType?.let {
                        matchContentType(
                            *it.toTypedArray()
                        )
                    }
                    it.excludeContentType?.let {
                        excludeContentType(
                            *it.toTypedArray()
                        )
                    }
                }
            }
        }
    }
}