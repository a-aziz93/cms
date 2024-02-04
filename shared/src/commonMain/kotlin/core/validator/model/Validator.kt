package core.validator.model

import core.message.model.Message
import kotlinx.serialization.Serializable


@Serializable
open class Validator(
    val name: String,
    val regex: String,
    val successMsg: Message?=null,
    val failureMsg: Message?=null,
    val description: String? = null,
)