package digital.sadad.project.cms.scansource.service

import digital.sadad.project.cms.scanmapper.repository.ScanMapperRepository
import digital.sadad.project.cms.scanmapper.service.ScanMapperService
import digital.sadad.project.cms.scansource.repository.ScanSourceRepository

class ScanSourceServiceImpl(
    override val crudRepository: ScanSourceRepository,
) : ScanSourceService {
}