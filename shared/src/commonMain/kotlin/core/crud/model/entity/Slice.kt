package core.crud.model.entity

import kotlinx.serialization.Serializable

@Serializable
data class Slice(
    private val offset: Long = 0,
    val size: Long = 1,
    val isPage: Boolean? = null,
) {
    fun offset() = if (isPage == true) offset * size else offset
}
