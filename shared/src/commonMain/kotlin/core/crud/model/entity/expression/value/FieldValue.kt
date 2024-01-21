package core.crud.model.entity.expression.value

import core.crud.model.entity.expression.variable.FieldVariable
import kotlinx.serialization.Serializable

@Serializable
open class FieldValue(override val value: String) : Value<String>,
    FieldVariable {

    companion object {
        fun field(name: String) = FieldValue(name)
    }
}