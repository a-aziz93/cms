package core.graph

import core.crud.repository.CRUDRepository
import core.crud.repository.model.expression.variable.BooleanVariable
import core.crud.repository.model.expression.variable.extension.eq
import core.crud.repository.model.expression.variable.extension.f
import core.graph.exception.GraphException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@Suppress("UNUSED")
abstract class Vertex<V : Vertex<V, ID, E, EID>, ID : Any, E : Edge<E, EID, V, ID>, EID : Any>(
    id: ID? = null,
    vertexObjects: CRUDRepository<V, ID>? = null,
) : GraphObject<V, ID>(id, vertexObjects) {
    protected var edgeObjects: CRUDRepository<E, EID>? = null

    internal fun assignEdgeResources(edgeObjects: CRUDRepository<E, EID>) {
        this.edgeObjects = edgeObjects
    }

    @Suppress("UNUSED")
    @Throws(GraphException::class)
    suspend fun edges(predicate: BooleanVariable? = null): Flow<E> {
        if (id == null) {
            throw GraphException("Id is not provided")
        }

        if (graphObjects == null) {
            throw GraphException("Graph objects is not provided")
        }

        val idPredicate = "fromVertexId".f().eq(id).or(
            "toVertexId".f().eq(id)
        )

        return (edgeObjects ?: throw GraphException("Edge graph objects is not provided")).find(
            predicate = if (predicate == null)
                idPredicate else idPredicate.and(predicate)
        ).onEach {
            it.assignStartVertexResources(id, graphObjects)
        }
    }
}