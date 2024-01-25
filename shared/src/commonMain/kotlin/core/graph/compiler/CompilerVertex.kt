package core.graph.compiler

import core.crud.repository.CRUDRepository
import core.graph.Vertex
import graph.compiler.CompilerVertexType

class CompilerVertex<ID : Any, EID : Any>(
    id: ID? = null,
    val type: CompilerVertexType,
    val payload: Any? = null,
    vertexObjects: CRUDRepository<CompilerVertex<ID, EID>, ID>? = null,
) : Vertex<CompilerVertex<ID, EID>,ID, CompilerEdge<EID, ID>,  EID>(id, vertexObjects)