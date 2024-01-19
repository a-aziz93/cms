package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class CharCollectionValue private constructor(override val value: Collection<Char>) : CollectionValue<Char> {
    companion object {
        fun charCollection(value: Collection<Char>) = CharCollectionValue(value)
    }
}
