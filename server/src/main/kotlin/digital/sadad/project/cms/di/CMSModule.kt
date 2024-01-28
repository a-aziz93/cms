package digital.sadad.project.cms.di

import digital.sadad.project.cms.scan.model.entity.ScanTable
import digital.sadad.project.cms.scan.repository.ScanCRUDRepository
import digital.sadad.project.cms.scan.service.ScanService
import digital.sadad.project.cms.scan.service.ScanServiceImpl
import digital.sadad.project.cms.scanmapper.model.entity.ScanMapperTable
import digital.sadad.project.cms.scanmapper.repository.ScanMapperCRUDRepository
import digital.sadad.project.cms.scanmapper.service.ScanMapperService
import digital.sadad.project.cms.scanmapper.service.ScanMapperServiceImpl
import digital.sadad.project.cms.scansource.model.entity.ScanSourceTable
import digital.sadad.project.cms.scansource.repository.ScanSourceCRUDRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.ufoss.kotysa.R2dbcSqlClient

fun Module.cmsModule(client: R2dbcSqlClient, name: String?) {
    // SCAN
    single<ScanCRUDRepository>(name?.let { named(it) }) { ScanCRUDRepository(client, ScanTable) }
    single<ScanService>(name?.let { named(it) }) { ScanServiceImpl(get(name?.let { named(it) })) }

    // SCANSOURCE
    single<ScanSourceCRUDRepository>(name?.let { named(it) }) { ScanSourceCRUDRepository(client, ScanSourceTable) }
    single<ScanService>(name?.let { named(it) }) { ScanServiceImpl(get(name?.let { named(it) })) }

    // SCANSOURCE
    single<ScanMapperCRUDRepository>(name?.let { named(it) }) { ScanMapperCRUDRepository(client, ScanMapperTable) }
    single<ScanMapperService>(name?.let { named(it) }) { ScanMapperServiceImpl(get(name?.let { named(it) })) }
}