package core.network

import io.ktor.client.*

expect fun createPlatformHttpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient