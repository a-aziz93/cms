package core.crud.model.entity.expression.predicate

interface CharVariable : core.crud.model.entity.expression.predicate.Variable {
    fun eq(value: core.crud.model.entity.expression.predicate.CharVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.eq(this, value)

    fun neq(value: core.crud.model.entity.expression.predicate.CharVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.neq(this, value)

    fun ln(value: core.crud.model.entity.expression.predicate.CharCollectionVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.ln(this, value)

    fun nin(value: core.crud.model.entity.expression.predicate.CharCollectionVariable) =
        core.crud.model.entity.expression.predicate.Predicate.Companion.nin(this, value)
}