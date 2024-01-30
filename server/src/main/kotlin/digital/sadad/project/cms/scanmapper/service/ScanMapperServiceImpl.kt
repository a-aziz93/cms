package digital.sadad.project.cms.scanmapper.service

import cms.scan.model.entity.ScanEntity
import core.expression.variable.extension.f
import core.extension.xmlProperties
import digital.sadad.project.cms.scan.repository.ScanCRUDRepository
import digital.sadad.project.cms.scanmapper.repository.ScanMapperCRUDRepository
import kotlinx.coroutines.flow.single
import org.apache.commons.net.ftp.FTPClient
import java.io.InputStream
import javax.xml.stream.XMLEventReader
import javax.xml.stream.XMLInputFactory


class ScanMapperServiceImpl(
    override val crudRepository: ScanMapperCRUDRepository,
) : ScanMapperService {

}