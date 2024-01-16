package core.graph

@Suppress("UNUSED")
interface Graph<VID : Any, V : Vertex<VID, V, EID, E>, EID : Any, E : Edge<EID, E, VID, V>> {
    fun vertices(): GraphObjects<VID, V>
    fun edges(): GraphObjects<EID, E>
    suspend fun inTransaction(transaction: suspend (graph: Graph<VID, V, EID, E>) -> Boolean)
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