package digital.sadad.project.map.markerpopup.repository

import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import map.markerpopup.model.entity.MarkerPopupEntity
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class MarkerPopupCRUDRepository(
    client: R2dbcSqlClient,
    table: Table<MarkerPopupEntity>,
) : KotysaCRUDRepository<MarkerPopupEntity, Long>(client, table)