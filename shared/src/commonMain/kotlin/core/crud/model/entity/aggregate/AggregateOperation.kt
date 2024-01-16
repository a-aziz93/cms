package core.crud.model.entity.aggregate

// An aggregate function ignores NULL values when it performs the calculation, except for the count function.
enum class AggregateOperation {
    COUNT,
    MAX,
    MIN,
    AVG,
    SUM,
}