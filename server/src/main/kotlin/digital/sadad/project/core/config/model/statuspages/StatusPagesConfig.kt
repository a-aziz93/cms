package digital.sadad.project.core.config.model.statuspages

import io.ktor.http.*

data class StatusPagesConfig(
    val status: List<StatusConfig>? = null,
    val statusFile: List<StatusFileConfig>? = null,
)
