package digital.sadad.project.cms.scansource.repository

import cms.scansource.model.entity.ScanSourceEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class ScanSourceCRUDRepository(
    client: R2dbcSqlClient,
    table: Table<ScanSourceEntity>,
) : KotysaCRUDRepository<ScanSourceEntity, Long>(client, table)