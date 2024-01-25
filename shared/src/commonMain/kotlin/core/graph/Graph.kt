package core.graph

import core.crud.repository.CRUDRepository

@Suppress("UNUSED")
interface Graph<V : Vertex<V, VID, E, EID>, VID : Any, E : Edge<E, EID, V, VID>, EID : Any> {
    fun vertices(): CRUDRepository<VID, V>
    fun edges(): CRUDRepository<EID, E>
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