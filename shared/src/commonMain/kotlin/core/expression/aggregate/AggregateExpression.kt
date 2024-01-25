package core.expression.aggregate

import kotlinx.serialization.Serializable

// An aggregate function ignores NULL values when it performs the calculation, except for the count function.
@Serializable
enum class AggregateExpression {
    COUNT,
    MAX,
    MIN,
    AVG,
    SUM,
}