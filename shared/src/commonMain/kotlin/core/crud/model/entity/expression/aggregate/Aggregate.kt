package core.crud.model.entity.expression.aggregate

import core.crud.model.entity.expression.projection.Projection
import core.crud.model.entity.expression.value.FieldValue
import core.crud.model.entity.expression.aggregate.AggregateExpression.*
import kotlinx.serialization.Serializable

@Serializable
class Aggregate private constructor(
    val operation: AggregateExpression,
    val projection: Projection? = null,
) {
    companion object {
        fun count(projection: Projection? = null) = Aggregate(COUNT, projection)
        fun max(projection: Projection? = null) = Aggregate(MAX, projection)
        fun min(projection: Projection? = null) = Aggregate(MIN, projection)
        fun avg(projection: Projection? = null) = Aggregate(AVG, projection)
        fun sum(projection: Projection? = null) = Aggregate(SUM, projection)
    }
}