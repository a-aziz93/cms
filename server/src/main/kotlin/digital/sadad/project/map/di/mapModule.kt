package digital.sadad.project.map.di

import digital.sadad.project.cms.scan.model.entity.ScanTable
import digital.sadad.project.cms.scan.repository.ScanRepository
import digital.sadad.project.cms.scan.service.ScanService
import digital.sadad.project.cms.scan.service.ScanServiceImpl
import digital.sadad.project.map.marker.model.entity.MarkerTable
import digital.sadad.project.map.marker.repository.MarkerRepository
import digital.sadad.project.map.marker.service.MarkerService
import digital.sadad.project.map.marker.service.MarkerServiceImpl
import digital.sadad.project.map.markerpopup.model.entity.MarkerPopupTable
import digital.sadad.project.map.markerpopup.repository.MarkerPopupRepository
import digital.sadad.project.map.markerpopup.service.MarkerPopupService
import digital.sadad.project.map.markerpopup.service.MarkerPopupServiceImpl
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.ufoss.kotysa.R2dbcSqlClient

fun Module.mapModule(client: R2dbcSqlClient, name: String?) {
    // MARKER
    single<MarkerRepository>(name?.let { named(it) }) { MarkerRepository(client, MarkerTable) }
    single<MarkerService>(name?.let { named(it) }) { MarkerServiceImpl(get(name?.let { named(it) })) }

    // MARKERPOPUP
    single<MarkerPopupRepository>(name?.let { named(it) }) { MarkerPopupRepository(client, MarkerPopupTable) }
    single<MarkerPopupService>(name?.let { named(it) }) { MarkerPopupServiceImpl(get(name?.let { named(it) })) }
}