package core.graph

import core.crud.model.entity.predicate.operation.Predicate
import core.crud.model.entity.predicate.value.PredicateField.Companion.field
import core.graph.exception.GraphException
import kotlinx.coroutines.flow.*

abstract class GraphObjects<ID : Any, T : GraphObject<ID, T>> {
    abstract fun getBy(predicate: Predicate? = null): Flow<T>
    suspend fun getById(id: ID): T? = getBy(field("id").eq(id)).singleOrNull()
    abstract suspend fun save(objects: Flow<T>): Flow<T>
    suspend fun save(objects: Iterable<T>): Iterable<T> = save(objects.asFlow()).toList()
    suspend fun save(vararg objects: T): Iterable<T> = save(objects.asList())
    abstract suspend fun removeBy(predicate: Predicate<*>? = null): Long
    suspend fun removeById(id: ID) = removeBy(field("id").eq(id)) == 1L
    suspend fun remove(obj: T): Boolean = removeById(obj.id ?: throw GraphException("Id is not provided"))
    abstract suspend fun count(predicate: Predicate? = null): Long
    open suspend fun exists(predicate: Predicate): Boolean = count(predicate) > 0
}