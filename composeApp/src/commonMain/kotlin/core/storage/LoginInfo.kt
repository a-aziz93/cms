package core.storage

import kotlinx.serialization.Serializable


@Serializable
data class LoginInfo(val username: String, val password: Int)
