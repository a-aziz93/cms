package core.crud.model.entity.expression.value

import core.crud.model.entity.expression.variable.*
import kotlinx.serialization.Serializable

@Serializable
open class FieldValue(override val value: String) : Value<String>,
    BooleanVariable, NumberVariable, StringVariable, TemporalVariable, CollectionVariable {

    companion object {
        fun field(name: String) = FieldValue(name)
    }
}