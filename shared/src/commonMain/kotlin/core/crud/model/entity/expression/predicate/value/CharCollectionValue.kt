package core.crud.model.entity.expression.predicate.value

import core.crud.model.entity.expression.predicate.CharCollectionVariable
import kotlinx.serialization.Serializable

@Serializable
class CharCollectionValue private constructor(override val value: Collection<Char>) : CollectionValue<Char>,
    core.crud.model.entity.expression.predicate.CharCollectionVariable {
    companion object {
        fun charCollection(value: Collection<Char>) = CharCollectionValue(value)
    }
}
