package digital.sadad.project.map.markerpopup.service

import digital.sadad.project.map.markerpopup.repository.MarkerPopupRepository

class MarkerPopupServiceImpl(
    override val crudRepository: MarkerPopupRepository,
) : MarkerPopupService {
}