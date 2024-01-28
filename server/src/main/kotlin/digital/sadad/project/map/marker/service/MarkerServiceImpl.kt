package digital.sadad.project.map.marker.service

import digital.sadad.project.map.marker.repository.MarkerCRUDRepository

class MarkerServiceImpl(
    override val crudRepository: MarkerCRUDRepository,
) : MarkerService {
}