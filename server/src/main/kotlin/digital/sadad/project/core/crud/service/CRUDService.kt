package digital.sadad.project.core.crud.service

import core.crud.CRUD
import core.crud.model.Order
import core.crud.model.Page
import core.crud.model.PageData
import core.crud.model.predicate.Predicate
import digital.sadad.project.core.crud.repository.CRUDRepository
import kotlinx.coroutines.flow.Flow

abstract class CRUDService<T : Any, ID : Any>(
    protected val repository: CRUDRepository<T, ID>
) : CRUD<T, ID> {
    override suspend fun save(entities: List<T>, updateIfExists: Boolean, byUsername: String?): List<T> =
        repository.save(entities, updateIfExists, byUsername)

    override suspend fun find(id: ID): T? = repository.find(id)

    override suspend fun find(projections: List<String>?, predicate: Predicate?, sort: List<Order>?): Flow<T> =
        repository.find(projections, predicate, sort)

    override suspend fun find(
        page: Page,
        projections: List<String>?,
        predicate: Predicate?,
        sort: List<Order>?
    ): PageData<T> = repository.find(page, projections, predicate, sort)

    override suspend fun delete(id: ID): Boolean = repository.delete(id)

    override suspend fun delete(predicate: Predicate?): Long = repository.delete(predicate)

    override suspend fun count(predicate: Predicate): Long = repository.count(predicate)
}