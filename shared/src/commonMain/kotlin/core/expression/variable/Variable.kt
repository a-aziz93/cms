package core.expression.variable

import core.expression.logic.LogicExpression
import core.expression.logic.LogicExpression.Companion.eq
import core.expression.logic.LogicExpression.Companion.neq

interface Variable {

    fun eq(value: Variable) =
        eq(this, value)

    fun neq(value: Variable) =
        neq(this, value)

    //TODO
    @Suppress("UNUSED")
    fun <D : Any> convert(converter: (BooleanVariable) -> D): D {
        val variables = mutableListOf<Triple<Variable, Int, D?>>(Triple(this, 0, null))

        while (variables.size > 0) {

        }

        return variables.first().third!!
    }
}