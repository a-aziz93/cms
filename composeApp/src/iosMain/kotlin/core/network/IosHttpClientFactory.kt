package core.network

import io.ktor.client.*
import io.ktor.client.engine.darwin.Darwin

actual fun createPlatformHttpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(Darwin,config)
}