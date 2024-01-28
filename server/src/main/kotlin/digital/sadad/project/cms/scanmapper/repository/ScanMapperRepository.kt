package digital.sadad.project.cms.scanmapper.repository

import cms.scan.model.entity.ScanEntity
import cms.scanmapper.model.entity.ScanMapperEntity
import core.crud.repository.CRUDRepository
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class ScanMapperRepository(
    client: R2dbcSqlClient,
    table: Table<ScanMapperEntity>,
) : KotysaCRUDRepository<ScanMapperEntity, Long>(client, table)