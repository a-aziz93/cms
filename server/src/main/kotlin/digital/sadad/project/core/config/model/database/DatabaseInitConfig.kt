package digital.sadad.project.core.config.model.database

data class DatabaseInitConfig(
    val ifNotExists: Boolean = true,
    val clearBefore: Boolean = false,
)