package digital.sadad.project.cms.scanmapper.repository

import cms.scanmapper.model.entity.ScanMapperEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class ScanMapperCRUDRepository(
    client: R2dbcSqlClient,
    table: Table<ScanMapperEntity>,
) : KotysaCRUDRepository<ScanMapperEntity, Long>(client, table)