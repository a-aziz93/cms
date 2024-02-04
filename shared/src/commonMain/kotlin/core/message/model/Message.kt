package core.message.model

import kotlinx.serialization.Serializable

@Serializable
open class Message {
    val value: String,
}