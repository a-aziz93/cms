package core.crud.model.entity.predicate.value

import core.crud.model.entity.predicate.CharCollectionVariable
import kotlinx.serialization.Serializable

@Serializable
class CharCollectionValue private constructor(override val value: Collection<Char>) : CollectionValue<Char>,
    CharCollectionVariable {
    companion object {
        fun charCollection(value: Collection<Char>) = CharCollectionValue(value)
    }
}
