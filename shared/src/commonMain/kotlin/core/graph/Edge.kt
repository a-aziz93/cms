package core.graph

import core.crud.CRUD
import core.crud.model.entity.expression.extension.f
import core.crud.model.entity.expression.logic.extension.eq
import core.graph.exception.GraphException
import kotlinx.coroutines.flow.firstOrNull
import kotlin.coroutines.cancellation.CancellationException

@Suppress("UNUSED")
abstract class Edge<E : Edge<E, ID, V, VID>, ID : Any, V : Vertex<V, VID, E, ID>, VID : Any>(
    id: ID?,
    val fromVertexId: VID,
    val toVertexId: VID,
    edgeObjects: CRUD<E, ID>? = null,
) : GraphObject<E, ID>(id, edgeObjects) {
    protected var startVertexId: VID? = null
    protected var vertexObjects: CRUD<V, VID>? = null
    internal fun assignStartVertexResources(
        startVertexId: VID,
        vertexObjects: CRUD<V, VID>
    ) {
        this.startVertexId = startVertexId
        this.vertexObjects = vertexObjects
    }

    @Throws(GraphException::class)
    fun isStartVertexFrom(): Boolean =
        (startVertexId ?: throw GraphException("Start vertex id is not provided")) == fromVertexId

    @Suppress("UNUSED")
    @Throws(GraphException::class)
    fun isStartVertexTo() = !isStartVertexFrom()

    @Suppress("UNUSED")
    @Throws(GraphException::class, CancellationException::class)
    suspend fun endVertex(): V? {
        if (graphObjects == null) {
            throw GraphException("Graph objects is not provided")
        }

        return (vertexObjects ?: throw GraphException("Vertex graph objects is not provided"))
            .find(predicate = "id".f().eq(if (isStartVertexFrom()) toVertexId else fromVertexId)).firstOrNull()
            ?.apply { assignEdgeResources(graphObjects) }
    }
}