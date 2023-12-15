package core.network

import io.ktor.client.*
import io.ktor.client.engine.java.*

actual fun createPlatformHttpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(Java,config)
}