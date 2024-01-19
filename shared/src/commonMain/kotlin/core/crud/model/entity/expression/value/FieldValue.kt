package core.crud.model.entity.expression.value

import core.crud.model.entity.expression.BooleanVariable
import core.crud.model.entity.expression.NumberVariable
import core.crud.model.entity.expression.StringVariable
import core.crud.model.entity.expression.TemporalVariable
import kotlinx.serialization.Serializable

@Serializable
open class FieldValue(override val value: String) : Value<String>,
    BooleanVariable,
    NumberVariable,
    StringVariable,
    TemporalVariable {

    companion object {
        fun field(name: String) = FieldValue(name)
    }
}