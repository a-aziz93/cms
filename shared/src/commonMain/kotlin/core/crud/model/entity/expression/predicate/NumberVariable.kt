package core.crud.model.entity.expression.predicate

interface NumberVariable : core.crud.model.entity.expression.predicate.Variable {
    fun eq(value: core.crud.model.entity.expression.predicate.NumberVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.eq(this, value)

    fun neq(value: core.crud.model.entity.expression.predicate.NumberVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.neq(this, value)

    fun gt(value: core.crud.model.entity.expression.predicate.NumberVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.gt(this, value)

    fun gte(value: core.crud.model.entity.expression.predicate.NumberVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.gte(this, value)

    fun lt(value: core.crud.model.entity.expression.predicate.NumberVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.lt(this, value)

    fun lte(value: core.crud.model.entity.expression.predicate.NumberVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.lte(this, value)

    fun between(leftValue: core.crud.model.entity.expression.predicate.NumberVariable, rightValue: core.crud.model.entity.expression.predicate.NumberVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.between(this, leftValue, rightValue)

    fun ln(value: core.crud.model.entity.expression.predicate.NumberCollectionVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.ln(this, value)

    fun nin(value: core.crud.model.entity.expression.predicate.NumberCollectionVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.nin(this, value)
}