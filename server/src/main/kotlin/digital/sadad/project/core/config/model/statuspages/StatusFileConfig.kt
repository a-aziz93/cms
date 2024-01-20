package digital.sadad.project.core.config.model.statuspages

import io.ktor.http.*

data class StatusFileConfig(
    val codes: List<HttpStatusCode>,
    val filePattern: String,
)