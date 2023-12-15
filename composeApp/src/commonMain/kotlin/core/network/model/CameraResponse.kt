package core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CameraResponse<T:Any>(
    val results: List<T>,
    val page:Long,
    val totalPages:Long,
)