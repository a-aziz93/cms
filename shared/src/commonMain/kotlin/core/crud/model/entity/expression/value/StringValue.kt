package core.crud.model.entity.expression.value

import core.crud.model.entity.expression.variable.StringVariable
import kotlinx.serialization.Serializable

@Serializable
open class StringValue private constructor(override val value: String) : Value<String>, StringVariable {

    companion object {
        fun string(value: String) = StringValue(value)
    }
}
