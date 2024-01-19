package core.graph

import core.crud.CRUD

abstract class GraphObjects<T : GraphObject<T, ID>, ID : Any> : CRUD<T, ID> {

}