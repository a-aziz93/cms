package digital.sadad.project.cms.scansource.repository

import cms.scanmapper.model.entity.ScanMapperEntity
import cms.scansource.model.entity.ScanSourceEntity
import core.crud.repository.CRUDRepository
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class ScanSourceRepository(
    client: R2dbcSqlClient,
    table: Table<ScanSourceEntity>,
) : KotysaCRUDRepository<ScanSourceEntity, Long>(client, table)