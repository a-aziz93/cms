package core.expression.variable

import core.crud.repository.model.expression.logic.Logic

interface StringVariable : Variable {
    fun eq(value: StringVariable) =
        Logic.eq(this, value)

    fun neq(value: StringVariable) =
        Logic.neq(this, value)

    fun like(value: StringVariable) =
        Logic.like(this, value)

    fun `in`(value: StringCollectionVariable) =
        Logic.`in`(this, value)

    fun nin(value: StringCollectionVariable) =
        Logic.nin(this, value)
}