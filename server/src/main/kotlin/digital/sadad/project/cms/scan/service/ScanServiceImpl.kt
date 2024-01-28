package digital.sadad.project.cms.scan.service

import digital.sadad.project.cms.scan.repository.ScanCRUDRepository

class ScanServiceImpl(
    override val crudRepository: ScanCRUDRepository,
) : ScanService {

}