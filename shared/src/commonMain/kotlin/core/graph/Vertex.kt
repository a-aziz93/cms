package core.graph

import core.graph.exception.GraphException
import graph.predicate.PredicateField.Companion.field
import graph.predicate.Predicate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@Suppress("UNUSED")
abstract class Vertex<ID : Any, V : Vertex<ID, V, EID, E>, EID : Any, E : Edge<EID, E, ID, V>>(
    id: ID? = null,
    vertexObjects: GraphObjects<ID, V>? = null,
) : GraphObject<ID, V>(id, vertexObjects) {
    protected var edgeObjects: GraphObjects<EID, E>? = null

    internal fun assignEdgeResources(edgeObjects: GraphObjects<EID, E>) {
        this.edgeObjects = edgeObjects
    }

    @Suppress("UNUSED")
    @Throws(GraphException::class)
    fun edges(predicate: Predicate<*>? = null): Flow<E> {
        if (id == null) {
            throw GraphException("Id is not provided")
        }

        if (graphObjects == null) {
            throw GraphException("Graph objects is not provided")
        }

        val idPredicate = field("fromVertexId").eq(id).or(
            field("toVertexId").eq(id)
        )

        return (edgeObjects ?: throw GraphException("Edge graph objects is not provided")).getBy(
            if (predicate == null)
                idPredicate else idPredicate.and(predicate)
        ).onEach {
            it.assignStartVertexResources(id, graphObjects)
        }
    }
}