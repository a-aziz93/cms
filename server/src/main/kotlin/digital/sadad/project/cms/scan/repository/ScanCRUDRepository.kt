package digital.sadad.project.cms.scan.repository

import cms.scan.model.entity.ScanEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class ScanCRUDRepository(
    client: R2dbcSqlClient,
    table: Table<ScanEntity>,
) : KotysaCRUDRepository<ScanEntity, Long>(client, table)