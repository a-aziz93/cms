package core.expression.aggregate

import core.expression.aggregate.AggregateExpressionType.*
import core.expression.projection.Projection
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
class AggregateExpression<T : Any> private constructor(
    val type: AggregateExpressionType,
    val projection: Projection? = null,
) {
    companion object {
        fun count(projection: Projection? = null) = AggregateExpression<Long>(COUNT, projection)
        fun <T : Any> max(projection: Projection) = AggregateExpression<T>(MAX, projection)
        fun <T : Any> min(projection: Projection) = AggregateExpression<T>(MIN, projection)
        fun avg(projection: Projection) = AggregateExpression<Number>(AVG, projection)
        fun sum(projection: Projection) = AggregateExpression<Number>(SUM, projection)
    }
}