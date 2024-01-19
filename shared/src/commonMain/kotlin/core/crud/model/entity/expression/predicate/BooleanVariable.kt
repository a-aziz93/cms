package core.crud.model.entity.expression.predicate

interface BooleanVariable : core.crud.model.entity.expression.predicate.Variable {
    fun eq(value: core.crud.model.entity.expression.predicate.BooleanVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.eq(this, value)

    fun neq(value: core.crud.model.entity.expression.predicate.BooleanVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.neq(this, value)

    fun and(vararg values: core.crud.model.entity.expression.predicate.BooleanVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.and(this, *values)

    fun or(vararg values: core.crud.model.entity.expression.predicate.BooleanVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.or(this, *values)

    fun xor(vararg values: core.crud.model.entity.expression.predicate.BooleanVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.xor(this, *values)

    fun no() = core.crud.model.entity.expression.predicate.Predicate.Companion.no(this)
}