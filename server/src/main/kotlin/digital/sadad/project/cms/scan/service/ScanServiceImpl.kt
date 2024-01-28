package digital.sadad.project.cms.scan.service

import digital.sadad.project.cms.scan.repository.ScanRepository

class ScanServiceImpl(
    override val crudRepository: ScanRepository,
) : ScanService {
}