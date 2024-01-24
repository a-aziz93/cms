package digital.sadad.project.core.plugin.graphql

import com.apurebase.kgraphql.GraphQL
import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.plugin.graphql.GraphQLConfig
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureGraphQL(config: GraphQLConfig) {
    if(config.enable==true) {
        install(GraphQL) {
            config.playground?.let { playground = it }
            config.endpoint?.let { endpoint = it }
        }
    }
}