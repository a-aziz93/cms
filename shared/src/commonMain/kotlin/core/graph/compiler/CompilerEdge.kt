package core.graph.compiler

import core.crud.repository.CRUDRepository
import core.graph.Edge

class CompilerEdge<ID : Any, VID : Any>(
    id: ID?,
    fromVertexId: VID,
    toVertexId: VID,
    val type: CompilerEdgeType,
    val payload: Any,
    edgeObjects: CRUDRepository<CompilerEdge<ID, VID>>? = null,
) : Edge<CompilerEdge<ID, VID>, ID, CompilerVertex<VID, ID>, VID>(
    id,
    fromVertexId,
    toVertexId,
    edgeObjects
)