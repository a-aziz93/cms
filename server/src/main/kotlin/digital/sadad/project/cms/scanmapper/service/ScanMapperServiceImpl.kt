package digital.sadad.project.cms.scanmapper.service

import digital.sadad.project.cms.scan.repository.ScanRepository
import digital.sadad.project.cms.scan.service.ScanService
import digital.sadad.project.cms.scanmapper.repository.ScanMapperRepository

class ScanMapperServiceImpl(
    override val crudRepository: ScanMapperRepository,
) : ScanMapperService {
}