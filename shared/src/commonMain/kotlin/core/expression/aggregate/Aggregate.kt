package core.expression.aggregate

import core.expression.aggregate.AggregateExpression.*
import core.expression.projection.Projection
import kotlinx.serialization.Serializable

@Serializable
class Aggregate private constructor(
    val operation: AggregateExpression,
    val projection: Projection? = null,
) {
    companion object {
        fun count(projection: Projection? = null) = Aggregate(COUNT, projection)
        fun max(projection: Projection) = Aggregate(MAX, projection)
        fun min(projection: Projection) = Aggregate(MIN, projection)
        fun avg(projection: Projection) = Aggregate(AVG, projection)
        fun sum(projection: Projection) = Aggregate(SUM, projection)
    }
}