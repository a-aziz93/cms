package digital.sadad.project.map.service.markerpopup

import digital.sadad.project.map.repository.marker.MarkerRepository
import digital.sadad.project.map.repository.markerpopup.MarkerPopupRepository

class MarkerPopupServiceImpl(
    override val crudRepository: MarkerPopupRepository,
) : MarkerPopupService {
}