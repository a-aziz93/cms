package digital.sadad.project.core.plugin.compression

import digital.sadad.project.core.config.model.plugin.compression.CompressionConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*


fun Application.configureCompression(config: CompressionConfig) {
    if (config.enable == true) {
        // We can configure compression here
        install(Compression) {
            //GZIP
            config.gzip?.let {
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
            config.deflate?.let {
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
            config.identity?.let {
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