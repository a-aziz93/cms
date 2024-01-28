package digital.sadad.project.map.markerpopup.service

import digital.sadad.project.map.markerpopup.repository.MarkerPopupCRUDRepository

class MarkerPopupServiceImpl(
    override val crudRepository: MarkerPopupCRUDRepository,
) : MarkerPopupService {
}