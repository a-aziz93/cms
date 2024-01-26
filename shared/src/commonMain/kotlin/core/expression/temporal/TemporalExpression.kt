package core.expression.temporal

import core.expression.variable.StringVariable
import core.expression.variable.TemporalVariable
import core.expression.temporal.TemporalExpressionType.*
import core.expression.variable.Variable

class TemporalExpression(
    val type: TemporalExpressionType,
    vararg val values: Variable,
) : StringVariable {

    companion object {
        fun now() = TemporalExpression(NOW)

        fun time(value: TemporalVariable) = TemporalExpression(TIME, value)

        fun date(value: TemporalVariable) = TemporalExpression(DATE, value)

        fun datetime(value: TemporalVariable) = TemporalExpression(DATETIME, value)

        fun datetimeOffset(value: TemporalVariable) = TemporalExpression(DATETIME_OFFSET, value)

        fun format(value: TemporalVariable, format: StringVariable) = TemporalExpression(FORMAT, value, format)
    }
}