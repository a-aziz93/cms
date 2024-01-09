package core.crud.repository

import core.crud.model.request.DataRequest
import core.crud.model.request.predicate.Predicate
import kotlinx.coroutines.flow.Flow

interface CrudRepository<T : Any, ID : Any> {

    suspend fun save(
        entities: List<T>,
        updateIfExists:Boolean,
        byUsername: String?
    ): List<T>

    suspend fun find(id: ID): T?

    suspend fun find(predicate: Predicate? = null,sort:List<Order>?=null): Flow<T>

    suspend fun find(predicate: Predicate? = null,sort:List<Order>?=null,page:Page): Flow<PageData<T>>
    
    suspend fun delete(id: ID): Boolean

    suspend fun delete(predicate: Predicate? = null): Long

    suspend fun count(predicate: Predicate): Long
}
