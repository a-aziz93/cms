package digital.sadad.project.core.crud.service

import core.crud.CRUD
import core.crud.model.entity.Order
import core.crud.model.entity.Slice
import core.crud.model.entity.PageResult
import core.crud.model.entity.Update
import core.crud.model.predicate.operation.Predicate
import digital.sadad.project.core.crud.repository.CRUDRepository
import kotlinx.coroutines.flow.Flow

abstract class CRUDService<T : Any, ID : Any>(
    protected val repository: CRUDRepository<T, ID>
) : CRUD<T, ID> {
    override suspend fun save(
        entities: List<T>,
        update: Update?,
        byUser: String?
    ): List<T> =
        repository.save(entities, update, byUser)

    override suspend fun find(id: ID): T? = repository.find(id)

    override suspend fun find(projections: List<String>?, predicate: Predicate?, sort: List<Order>?): Flow<T> =
        repository.find(projections, predicate, sort)

    override suspend fun find(
        page: Slice,
        projections: List<String>?,
        predicate: Predicate?,
        sort: List<Order>?
    ): PageResult<T> = repository.find(page, projections, predicate, sort)

    override suspend fun delete(id: ID): Boolean = repository.delete(id)

    override suspend fun delete(predicate: Predicate?): Long = repository.delete(predicate)

    override suspend fun count(predicate: Predicate?): Long = repository.count(predicate)
}