package digital.sadad.project.map.marker.service

import digital.sadad.project.cms.scan.service.ScanService
import digital.sadad.project.map.marker.repository.MarkerRepository

class MarkerServiceImpl(
    override val crudRepository: MarkerRepository,
) : ScanService {
}