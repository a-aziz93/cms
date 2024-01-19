package core.crud.model.entity.expression.value

import core.crud.model.entity.expression.StringCollectionVariable
import kotlinx.serialization.Serializable

@Serializable
open class StringCollectionValue private constructor(override val value: Collection<String>) : CollectionValue<String>,
    StringCollectionVariable {
    companion object {
        fun stringCollection(value: Collection<String>) = StringCollectionValue(value)
    }
}
