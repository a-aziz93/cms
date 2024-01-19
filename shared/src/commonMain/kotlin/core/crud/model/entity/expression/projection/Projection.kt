package core.crud.model.entity.expression.projection

import core.crud.model.entity.expression.value.FieldValue
import kotlinx.serialization.Serializable

class Projection private constructor(
    value: String,
    @Serializable
    val distinct: Boolean? = null,
) : FieldValue(value) {

    companion object {
        fun projection(value: String, distinct: Boolean? = null) = Projection(value, distinct)
    }
}