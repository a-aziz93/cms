package digital.sadad.project.core.plugins

import com.apurebase.kgraphql.GraphQL
import digital.sadad.project.core.config.AppConfig
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureGraphQL() {
    val appConfig: AppConfig by inject()
    val graphqlConfig = appConfig.config.graphql

    if (graphqlConfig != null) {
        install(GraphQL) {
            playground = graphqlConfig.playground ?: true
            endpoint = graphqlConfig.endpoint ?: "/graphql"
        }
    }
}