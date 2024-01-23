package digital.sadad.project.core.config.model.plugin.statuspages

import io.ktor.http.*

data class StatusConfig(
    val codes: List<HttpStatusCode>,
    val text: String,
)