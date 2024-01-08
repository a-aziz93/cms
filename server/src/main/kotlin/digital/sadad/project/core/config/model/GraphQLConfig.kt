package digital.sadad.project.core.config.model

data class GraphQLConfig(
    val playground: Boolean = false,
    val endpoint: String = "/graphql",
)
