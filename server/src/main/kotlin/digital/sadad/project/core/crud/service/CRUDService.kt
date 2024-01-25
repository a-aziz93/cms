package digital.sadad.project.core.crud.service

import core.crud.repository.CRUDRepository
import core.crud.repository.model.io.Order
import core.crud.repository.model.io.LimitOffset
import core.crud.repository.model.io.PageResult
import core.crud.repository.model.transaction.UpdateTransaction
import core.crud.model.predicate.operation.Predicate
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import kotlinx.coroutines.flow.Flow

abstract class CRUDService<T : Any, ID : Any>(
    protected val repository: KotysaCRUDRepository<T, ID>
) : CRUDRepository<T, ID> {
    override suspend fun save(
        entities: List<T>,
        update: UpdateTransaction?,
        byUser: String?
    ): List<T> =
        repository.save(entities, update, byUser)

    override suspend fun find(id: ID): T? = repository.find(id)

    override suspend fun find(projections: List<String>?, predicate: Predicate?, sort: List<Order>?): Flow<T> =
        repository.find(projections, predicate, sort)

    override suspend fun find(
        page: LimitOffset,
        projections: List<String>?,
        predicate: Predicate?,
        sort: List<Order>?
    ): PageResult<T> = repository.find(page, projections, predicate, sort)

    override suspend fun delete(id: ID): Boolean = repository.delete(id)

    override suspend fun delete(predicate: Predicate?): Long = repository.delete(predicate)

    override suspend fun count(predicate: Predicate?): Long = repository.count(predicate)
}