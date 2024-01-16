package graph.compiler

import core.graph.compiler.CompilerEdge
import core.graph.GraphObjects
import core.graph.Vertex

class CompilerVertex<ID : Any, EID : Any>(
    id: ID? = null,
    val type: CompilerVertexType,
    val payload: Any? = null,
    vertexObjects: GraphObjects<ID, CompilerVertex<ID, EID>>? = null,
) : Vertex<ID, CompilerVertex<ID, EID>, EID, CompilerEdge<EID, ID>>(id, vertexObjects)