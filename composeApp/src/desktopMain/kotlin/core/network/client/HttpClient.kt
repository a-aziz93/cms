package core.network.client

import io.ktor.client.engine.*
import io.ktor.client.engine.java.*

actual fun createHttpClientEngine(): HttpClientEngine = Java.create()