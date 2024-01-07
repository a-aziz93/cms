package core.crud.repository

import core.crud.model.request.DataRequest
import core.crud.model.request.predicate.Predicate
import kotlinx.coroutines.flow.Flow

interface CrudRepository<T : Any, ID : Any> {

    suspend fun save(
        entities: List<T>,
        byUsername: String?
    ): List<T>

    suspend fun find(id: ID): T?

    suspend fun find(request: DataRequest? = null): Flow<T>

    suspend fun delete(id: ID): Boolean

    suspend fun delete(predicate: Predicate<*>? = null): Long

    suspend fun count(predicate: Predicate<*>): Long
}