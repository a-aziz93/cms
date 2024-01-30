package core.expression.projection

import core.expression.aggregate.AggregateExpression
import core.expression.aggregate.AggregateExpression.Companion.count
import core.expression.aggregate.AggregateExpression.Companion.max
import core.expression.aggregate.AggregateExpression.Companion.min
import core.expression.aggregate.AggregateExpression.Companion.avg
import core.expression.aggregate.AggregateExpression.Companion.sum
import core.expression.aggregate.AggregateExpressionType
import core.expression.variable.Variable
import kotlinx.serialization.Serializable

@Serializable
class Projection private constructor(
    val value: String,
    val distinct: Boolean? = null,
) : Variable {
    fun count() = count(this)
    fun max() = max(this)
    fun min() = min(this)
    fun avg() = (this)
    fun sum() = sum(this)

    companion object {
        fun projection(value: String, distinct: Boolean? = null) = Projection(value, distinct)
    }
}