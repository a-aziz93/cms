package core.expression.aggregate

import core.expression.aggregate.AggregateExpressionType.*
import core.expression.projection.Projection
import kotlinx.serialization.Serializable

@Serializable
class AggregateExpression private constructor(
    val type: AggregateExpressionType,
    val projection: Projection? = null,
) {
    companion object {
        fun count(projection: Projection? = null) = AggregateExpression(COUNT, projection)
        fun max(projection: Projection) = AggregateExpression(MAX, projection)
        fun min(projection: Projection) = AggregateExpression(MIN, projection)
        fun avg(projection: Projection) = AggregateExpression(AVG, projection)
        fun sum(projection: Projection) = AggregateExpression(SUM, projection)
    }
}