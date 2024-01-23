package digital.sadad.project.core.config.model.plugin.cors

data class CORSHostConfig(
    val host: String,
    val schemes: List<String> = listOf("http"),
    val subDomains: List<String> = emptyList()
)
