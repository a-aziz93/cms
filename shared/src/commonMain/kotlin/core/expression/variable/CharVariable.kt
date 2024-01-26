package core.expression.variable

import core.expression.logic.LogicExpression
import core.expression.logic.LogicExpression.Companion.eq
import core.expression.logic.LogicExpression.Companion.neq
import core.expression.logic.LogicExpression.Companion.`in`
import core.expression.logic.LogicExpression.Companion.nin

interface CharVariable : Variable {
    fun `in`(value: CharCollectionVariable) =
        `in`(this, value)

    fun nin(value: CharCollectionVariable) =
        nin(this, value)
}