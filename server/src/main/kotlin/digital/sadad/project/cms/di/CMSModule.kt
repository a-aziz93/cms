package digital.sadad.project.cms.di

import cms.scan.model.entity.ScanEntity
import digital.sadad.project.cms.scan.model.entity.ScanTable
import digital.sadad.project.cms.scan.repository.ScanRepository
import digital.sadad.project.cms.scan.service.ScanService
import digital.sadad.project.cms.scan.service.ScanServiceImpl
import digital.sadad.project.cms.scanmapper.model.entity.ScanMapperTable
import digital.sadad.project.cms.scanmapper.repository.ScanMapperRepository
import digital.sadad.project.cms.scanmapper.service.ScanMapperService
import digital.sadad.project.cms.scanmapper.service.ScanMapperServiceImpl
import digital.sadad.project.cms.scansource.model.entity.ScanSourceTable
import digital.sadad.project.cms.scansource.repository.ScanSourceRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.ufoss.kotysa.R2dbcSqlClient

fun Module.cmsModule(client: R2dbcSqlClient, name: String?) {
    // SCAN
    single<ScanRepository>(name?.let { named(it) }) { ScanRepository(client, ScanTable) }
    single<ScanService>(name?.let { named(it) }) { ScanServiceImpl(get(name?.let { named(it) })) }

    // SCANSOURCE
    single<ScanSourceRepository>(name?.let { named(it) }) { ScanSourceRepository(client, ScanSourceTable) }
    single<ScanService>(name?.let { named(it) }) { ScanServiceImpl(get(name?.let { named(it) })) }

    // SCANSOURCE
    single<ScanMapperRepository>(name?.let { named(it) }) { ScanMapperRepository(client, ScanMapperTable) }
    single<ScanMapperService>(name?.let { named(it) }) { ScanMapperServiceImpl(get(name?.let { named(it) })) }
}