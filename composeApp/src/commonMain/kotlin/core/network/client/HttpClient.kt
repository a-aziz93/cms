package core.network.client

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.engine.*
import io.ktor.client.plugins.*

@Single
expect fun createHttpClientEngine(): HttpClientEngine

@Single
fun createHttpClient(
    httpClientEngine: HttpClientEngine, json: Json
): Ktorfit {
    val client = HttpClient(httpClientEngine) {
        defaultRequest { url("https://pokeapi.co/api/v2/") }
        install(ContentNegotiation) { json(json) }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }
    return Ktorfit.Builder().httpClient(client).build()
}
