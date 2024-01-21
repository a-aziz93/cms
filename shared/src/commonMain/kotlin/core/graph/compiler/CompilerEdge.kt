package core.graph.compiler

import core.crud.CRUD
import core.graph.Edge
import graph.compiler.CompilerEdgeType

class CompilerEdge<ID : Any, VID : Any>(
    id: ID?,
    fromVertexId: VID,
    toVertexId: VID,
    val type: CompilerEdgeType,
    val payload: Any,
    edgeObjects: CRUD<CompilerEdge<ID, VID>, ID>? = null,
) : Edge<CompilerEdge<ID, VID>, ID, CompilerVertex<VID, ID>, VID>(
    id,
    fromVertexId,
    toVertexId,
    edgeObjects
)