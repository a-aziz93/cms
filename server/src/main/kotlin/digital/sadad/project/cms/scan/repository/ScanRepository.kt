package digital.sadad.project.cms.scan.repository

import cms.scan.model.entity.ScanEntity
import core.crud.repository.CRUDRepository
import core.role.model.entity.RoleEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class ScanRepository(
    client: R2dbcSqlClient,
    table: Table<ScanEntity>,
) : KotysaCRUDRepository<ScanEntity, Long>(client, table)