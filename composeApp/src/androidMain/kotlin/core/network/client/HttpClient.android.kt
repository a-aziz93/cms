package core.network.client

import io.ktor.client.engine.*
import io.ktor.client.engine.android.*

actual fun createHttpClientEngine(): HttpClientEngine = Android.create()