package core.serialization

import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
fun json() = Json { isLenient = true; ignoreUnknownKeys = true }