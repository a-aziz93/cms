package core.crud.model.entity.expression.variable

import core.crud.model.entity.expression.logic.Logic
import core.crud.model.entity.expression.value.Value

interface Variable {

    //TODO
    @Suppress("UNUSED")
    fun <D : Any> convert(converter: (BooleanVariable) -> D): D {
        val variables = mutableListOf<Triple<Variable, Int, D?>>(Triple(this, 0, null))

        while (variables.size > 0) {

        }

        return variables.first().third!!
    }
}