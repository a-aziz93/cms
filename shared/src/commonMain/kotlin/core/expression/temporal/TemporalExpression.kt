package core.expression.temporal

import core.expression.variable.StringVariable
import core.expression.variable.TemporalVariable
import core.expression.temporal.TemporalExpressionType.*

class TemporalExpression(
    val type: TemporalExpressionType,
    vararg val values: TemporalVariable,
) : StringVariable {

    companion object {
        fun now(vararg values: StringVariable) = TemporalExpression(NOW)
    }
}