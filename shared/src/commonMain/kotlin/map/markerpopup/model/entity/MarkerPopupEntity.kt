package map.markerpopup.model.entity

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import map.markerpopup.model.MarkerPopup
import map.markerpopup.model.dto.MarkerPopupDto
import java.time.LocalDateTime

@KConMapper(
    toClasses = [MarkerPopup::class, MarkerPopupDto::class],
    fromClasses = [MarkerPopup::class, MarkerPopupDto::class],
)
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