package core.crud.model.entity.expression.aggregate

// An aggregate function ignores NULL values when it performs the calculation, except for the count function.
enum class AggregateExpression {
    COUNT,
    MAX,
    MIN,
    AVG,
    SUM,
}