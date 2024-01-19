package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class BooleanCollectionValue private constructor(override val value: Collection<Boolean>) : CollectionValue<Boolean> {
    companion object {
        fun booleanCollection(value: Collection<Boolean>) = BooleanCollectionValue(value)
    }
}
