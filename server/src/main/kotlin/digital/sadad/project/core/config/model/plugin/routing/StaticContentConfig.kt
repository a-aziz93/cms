package digital.sadad.project.core.config.model.plugin.routing

import digital.sadad.project.core.config.model.plugin.cachingheaders.CacheControlConfig
import io.ktor.http.*
import io.ktor.server.http.content.*

data class StaticContentConfig(
    val remotePath: String,
    val pathName: String,
    val index: String = "index.html",
    val defaultPath: String? = null,
    val enableAutoHeadResponse: Boolean? = null,
    val preCompressed: Set<CompressedFileType>? = null,
    val contentType: Map<String, ContentType>? = null,
    val cacheControl: Map<String, List<CacheControlConfig>>? = null,
    val excludePaths: Set<String>? = null,
    val extensions: Set<String>? = null,
)
