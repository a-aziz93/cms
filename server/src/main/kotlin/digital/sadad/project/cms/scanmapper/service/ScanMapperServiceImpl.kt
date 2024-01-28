package digital.sadad.project.cms.scanmapper.service

import digital.sadad.project.cms.scanmapper.repository.ScanMapperCRUDRepository
import digital.sadad.project.cms.scansource.repository.ScanSourceCRUDRepository

class ScanMapperServiceImpl(
    override val crudRepository: ScanMapperCRUDRepository,
) : ScanMapperService {

}