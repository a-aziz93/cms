package core.graph

import core.graph.exception.GraphException
import kotlin.coroutines.cancellation.CancellationException

@Suppress("UNUSED")
abstract class Edge<ID : Any, E : Edge<ID, E, VID, V>, VID : Any, V : Vertex<VID, V, ID, E>>(
    id: ID?,
    val fromVertexId: VID,
    val toVertexId: VID,
    edgeObjects: GraphObjects<ID, E>? = null,
) : GraphObject<ID, E>(id, edgeObjects) {
    protected var startVertexId: VID? = null
    protected var vertexObjects: GraphObjects<VID, V>? = null
    internal fun assignStartVertexResources(
        startVertexId: VID,
        vertexObjects: GraphObjects<VID, V>
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
            .getById(if (isStartVertexFrom()) toVertexId else fromVertexId)
            ?.apply { assignEdgeResources(graphObjects) }
    }
}