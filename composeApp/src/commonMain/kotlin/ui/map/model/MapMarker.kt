package ui.map.model

data class MapMarker(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double? = null,
    val icon: String? = null,
    val description: String? = null,
    val popup: MapPopup? = null,
)
