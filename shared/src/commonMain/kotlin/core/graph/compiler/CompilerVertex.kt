package core.graph.compiler

import core.crud.repository.CRUDRepository
import core.graph.Vertex

class CompilerVertex<ID : Any, EID : Any>(
    id: ID? = null,
    val type: CompilerVertexType,
    val payload: Any? = null,
    vertexObjects: CRUDRepository<CompilerVertex<ID, EID>>? = null,
) : Vertex<CompilerVertex<ID, EID>,ID, CompilerEdge<EID, ID>,  EID>(id, vertexObjects)