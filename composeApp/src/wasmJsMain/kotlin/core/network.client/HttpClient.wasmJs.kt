package core.network.client

import io.ktor.client.engine.*
import io.ktor.client.engine.js.*

actual fun createHttpClientEngine(): HttpClientEngine = Js.create()