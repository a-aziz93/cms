package core.network

import io.ktor.client.*
import io.ktor.client.engine.android.Android

actual fun createPlatformHttpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(Android, config)
}