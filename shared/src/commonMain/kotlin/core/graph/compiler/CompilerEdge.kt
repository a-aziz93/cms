package core.graph.compiler

import core.graph.Edge
import core.graph.GraphObjects
import graph.compiler.CompilerEdgeType
import graph.compiler.CompilerVertex

class CompilerEdge<ID : Any, VID : Any>(
    id: ID?,
    fromVertexId: VID,
    toVertexId: VID,
    val type: CompilerEdgeType,
    val payload: Any,
    edgeObjects: GraphObjects<ID, CompilerEdge<ID, VID>>? = null,
) : Edge<ID, CompilerEdge<ID, VID>, VID, CompilerVertex<VID, ID>>(
    id,
    fromVertexId,
    toVertexId,
    edgeObjects
)