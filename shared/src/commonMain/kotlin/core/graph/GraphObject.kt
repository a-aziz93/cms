package core.graph

import core.crud.CRUD
import core.crud.model.entity.expression.extension.f
import core.crud.model.entity.expression.logic.extension.eq
import core.graph.exception.GraphException
import kotlin.coroutines.cancellation.CancellationException

abstract class GraphObject<T : GraphObject<T, ID>, ID : Any>(
    val id: ID? = null,
    protected val objects: CRUD<T, ID>? = null
) {
    @Suppress("UNUSED", "UNCHECKED_CAST")
    @Throws(GraphException::class, CancellationException::class)
    suspend fun save(): T {
        if (objects == null) {
            throw GraphException("Graph objects is not provided")
        }

        return objects.save(listOf(this as T)).first()
    }

    @Suppress("UNUSED")
    @Throws(GraphException::class, CancellationException::class)
    suspend fun delete(): Boolean {
        if (objects == null) {
            throw GraphException("${this::class.simpleName} graph objects is not provided")
        }

        return objects.delete("id".f().eq(this.id)) > 0L
    }

    override fun equals(other: Any?): Boolean =
        this === other || (
                other != null && this::class == other::class &&
                        id == (other as GraphObject<*, *>).id)

    override fun hashCode(): Int = id.hashCode()

}