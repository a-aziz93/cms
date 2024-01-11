package core.data.storage.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginInfo(
    val username: String,
    val password: String,
    val role: String? = null,
)