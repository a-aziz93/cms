package core.expression.variable

import core.expression.logic.LogicExpression
import core.expression.temporal.TemporalExpression
import core.expression.temporal.TemporalExpressionType
import core.expression.temporal.TemporalExpression.Companion.time
import core.expression.temporal.TemporalExpression.Companion.date
import core.expression.temporal.TemporalExpression.Companion.datetime
import core.expression.temporal.TemporalExpression.Companion.datetimeOffset
import core.expression.temporal.TemporalExpression.Companion.format

interface TemporalVariable : ComparableVariable {

    fun time() = time(this)

    fun date() = date(this)

    fun datetime() = datetime(this)

    fun datetimeOffset() = datetime(this)

    fun format(format: StringVariable) = format(this, format)

    fun `in`(value: TemporalCollectionVariable) =
        LogicExpression.`in`(this, value)

    fun nin(value: TemporalCollectionVariable) =
        LogicExpression.nin(this, value)
}