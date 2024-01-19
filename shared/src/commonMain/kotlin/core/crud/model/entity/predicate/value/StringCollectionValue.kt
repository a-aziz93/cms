package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
open class StringCollectionValue private constructor(override val value: Collection<String>) : CollectionValue<String> {
    companion object {
        fun stringCollection(value: Collection<String>) = StringCollectionValue(value)
    }
}
