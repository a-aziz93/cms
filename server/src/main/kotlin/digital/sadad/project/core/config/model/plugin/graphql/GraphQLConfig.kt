package digital.sadad.project.core.config.model.plugin.graphql

import digital.sadad.project.core.config.model.plugin.PluginConfig

class GraphQLConfig(
    enable: Boolean? = null,
    val playground: Boolean? = null,
    val endpoint: String? = null,
) : PluginConfig(enable)
