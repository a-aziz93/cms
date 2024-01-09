package digital.sadad.project.core.extension.sse.model

data class SseEvent(
    val data: String,
    val event: String? = null,
    val id: String? = null,
)
