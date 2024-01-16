package core.crud.model.entity

import kotlinx.serialization.Serializable

@Serializable
data class Slice(
    private val offset: Long? = null,
    val size: Long? = null,
    val isPage: Boolean? = null,
) {
    fun offset() =
        if (isPage == true) {
            if (offset != null && size != null)
                offset * size else null
        } else offset
}
