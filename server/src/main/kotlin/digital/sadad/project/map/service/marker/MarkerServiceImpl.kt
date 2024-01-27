package digital.sadad.project.map.service.marker

import digital.sadad.project.cms.service.marker.ScanService
import digital.sadad.project.map.repository.marker.MarkerRepository

class MarkerServiceImpl(
    override val crudRepository: MarkerRepository,
) : ScanService {
}