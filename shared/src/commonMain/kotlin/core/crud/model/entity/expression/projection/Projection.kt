package core.crud.model.entity.expression.projection

import core.crud.model.entity.expression.value.FieldValue
import kotlinx.serialization.Serializable

@Serializable
class Projection private constructor(
    value: String,
) : FieldValue(value) {
    companion object {
        fun projection(field: FieldValue, distinct: Boolean? = null) = Projection(field, distinct)
    }
}