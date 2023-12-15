package core.network

import io.ktor.client.*
import io.ktor.client.engine.js.*

actual fun createPlatformHttpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient {
    return HttpClient(Js,config)
}