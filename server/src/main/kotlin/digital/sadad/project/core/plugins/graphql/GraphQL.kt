package digital.sadad.project.core.plugins.graphql

import com.apurebase.kgraphql.GraphQL
import digital.sadad.project.core.config.AppConfig
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureGraphQL() {
    val appConfig: AppConfig by inject()
    appConfig.config.graphql?.let {
        install(GraphQL) {
            it.playground?.let { playground = it }
            it.endpoint?.let { endpoint = it }
        }
    }
}