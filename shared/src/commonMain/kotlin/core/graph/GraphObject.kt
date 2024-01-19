package core.graph

import core.graph.exception.GraphException
import kotlin.coroutines.cancellation.CancellationException

abstract class GraphObject<T : GraphObject<T, ID>, ID : Any>(
    val id: ID? = null,
    protected val graphObjects: GraphObjects<T, ID>? = null
) {
    @Suppress("UNUSED", "UNCHECKED_CAST")
    @Throws(GraphException::class, CancellationException::class)
    suspend fun save(): T {
        if (graphObjects == null) {
            throw GraphException("core.graph.Graph objects is not provided")
        }

        return graphObjects.save(this as T).first()
    }

    @Suppress("UNUSED", "UNCHECKED_CAST")
    @Throws(GraphException::class, CancellationException::class)
    suspend fun remove(): Boolean {
        if (graphObjects == null) {
            throw GraphException("${this::class.simpleName} graph objects is not provided")
        }

        return graphObjects.remove(this as T)
    }

    override fun equals(other: Any?): Boolean =
        this === other || (
                other != null && this::class == other::class &&
                        id == (other as GraphObject<*, *>).id)

    override fun hashCode(): Int = id.hashCode()

}