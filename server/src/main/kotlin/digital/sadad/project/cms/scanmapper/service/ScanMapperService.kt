package digital.sadad.project.cms.scanmapper.service

import cms.scan.model.Scan
import cms.scan.model.entity.ScanEntity
import cms.scanmapper.model.entity.ScanMapperEntity
import core.crud.service.CRUDService
import org.apache.commons.net.ftp.FTPClient
import java.io.InputStream

interface ScanMapperService : CRUDService<ScanMapperEntity>