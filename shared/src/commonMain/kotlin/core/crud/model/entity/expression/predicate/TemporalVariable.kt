package core.crud.model.entity.expression.predicate

interface TemporalVariable : Variable {
    fun eq(value: TemporalVariable) =
        core.crud.model.entity.expression.predicate.Predicate.eq(this, value)

    fun neq(value: TemporalVariable) =
        core.crud.model.entity.expression.predicate.Predicate.neq(this, value)

    fun gt(value: TemporalVariable) =
        core.crud.model.entity.expression.predicate.Predicate.gt(this, value)

    fun gte(value: TemporalVariable) =
        core.crud.model.entity.expression.predicate.Predicate.gte(this, value)

    fun lt(value: TemporalVariable) =
        core.crud.model.entity.expression.predicate.Predicate.lt(this, value)

    fun lte(value: TemporalVariable) =
        core.crud.model.entity.expression.predicate.Predicate.lte(this, value)

    fun between(leftValue: TemporalVariable, rightValue: TemporalVariable) =
        core.crud.model.entity.expression.predicate.Predicate.between(this, leftValue, rightValue)

    fun ln(value: TemporalCollectionVariable) =
        core.crud.model.entity.expression.predicate.Predicate.ln(this, value)

    fun nin(value: TemporalCollectionVariable) =
        core.crud.model.entity.expression.predicate.Predicate.nin(this, value)
}