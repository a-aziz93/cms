package digital.sadad.project.map.marker.repository

import cms.scanmapper.model.entity.ScanMapperEntity
import core.crud.repository.CRUDRepository
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import map.marker.model.entity.MarkerEntity
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class MarkerRepository(
    client: R2dbcSqlClient,
    table: Table<MarkerEntity>,
) : KotysaCRUDRepository<MarkerEntity, Long>(client, table)