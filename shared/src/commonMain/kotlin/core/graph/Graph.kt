package core.graph

import core.crud.CRUD

@Suppress("UNUSED")
interface Graph<V : Vertex<V, VID, E, EID>, VID : Any, E : Edge<E, EID, V, VID>, EID : Any> {
    fun vertices(): CRUD<VID, V>
    fun edges(): CRUD<EID, E>
    suspend fun inTransaction(transaction: suspend (graph: Graph<V, VID, E, EID>) -> Boolean)
    suspend fun dispose()
}

@Suppress("UNUSED")
@Throws(Exception::class)
suspend fun <T : Graph<*, *, *, *>> T.use(block: (graph: T) -> Unit) {
    try {
        return block(this)
    } catch (e: Throwable) {
        throw e
    } finally {
        this.dispose()
    }
}