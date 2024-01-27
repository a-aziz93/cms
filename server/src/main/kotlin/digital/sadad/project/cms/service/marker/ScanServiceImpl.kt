package digital.sadad.project.cms.service.marker

import digital.sadad.project.cms.repository.scan.ScanRepository
import digital.sadad.project.map.repository.marker.MarkerRepository

class ScanServiceImpl(
    override val crudRepository: ScanRepository,
) : ScanService {
}