package digital.sadad.project.core.config.model.security

data class AuthSchemesConfig(
    val defaultScheme: String = "Bearer",
    val additionalSchemes: List<String> = emptyList(),
)
