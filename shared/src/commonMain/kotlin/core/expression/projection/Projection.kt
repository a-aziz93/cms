package core.expression.projection

import core.expression.variable.Variable
import kotlinx.serialization.Serializable

@Serializable
class Projection private constructor(
    val value: String,
    val distinct: Boolean? = null,
) : Variable {

    companion object {
        fun projection(value: String, distinct: Boolean? = null) = Projection(value, distinct)
    }
}