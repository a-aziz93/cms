package map.model.marker

open class Marker(
    val longitude: Double,
    val latitude: Double,
    val altitude: Double? = null,
    val description: String? = null,
)