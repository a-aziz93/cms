package map.model.markerpopup.entity

import map.model.markerpopup.MarkerPopup
import java.time.LocalDateTime

class MarkerPopupEntity(
    val id: Long? = null,
    title: String,
    val markerId: Long,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : MarkerPopup(
    title
)