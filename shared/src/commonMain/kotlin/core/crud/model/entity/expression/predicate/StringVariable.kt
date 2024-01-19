package core.crud.model.entity.expression.predicate

interface StringVariable : Variable {
    fun eq(value: StringVariable) =
        core.crud.model.entity.expression.predicate.Predicate.eq(this, value)

    fun neq(value: StringVariable) =
        core.crud.model.entity.expression.predicate.Predicate.neq(this, value)

    fun like(value: StringVariable) =
        core.crud.model.entity.expression.predicate.Predicate.like(this, value)

    fun ln(value: StringCollectionVariable) =
        core.crud.model.entity.expression.predicate.Predicate.ln(this, value)

    fun nin(value: StringCollectionVariable) =
        core.crud.model.entity.expression.predicate.Predicate.nin(this, value)
}