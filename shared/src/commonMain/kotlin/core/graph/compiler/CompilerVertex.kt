package core.graph.compiler

import core.crud.CRUD
import core.graph.Vertex
import graph.compiler.CompilerVertexType

class CompilerVertex<ID : Any, EID : Any>(
    id: ID? = null,
    val type: CompilerVertexType,
    val payload: Any? = null,
    vertexObjects: CRUD< CompilerVertex<ID, EID>,ID>? = null,
) : Vertex<CompilerVertex<ID, EID>,ID, CompilerEdge<EID, ID>,  EID>(id, vertexObjects)