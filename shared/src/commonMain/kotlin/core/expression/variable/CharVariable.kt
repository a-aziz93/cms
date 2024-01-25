package core.expression.variable

import core.crud.repository.model.expression.logic.Logic

interface CharVariable : Variable {
    fun eq(value: CharVariable) =
        Logic.Companion.eq(this, value)

    fun neq(value: CharVariable) =
        Logic.Companion.neq(this, value)

    fun `in`(value: CharCollectionVariable) =
        Logic.Companion.`in`(this, value)

    fun nin(value: CharCollectionVariable) =
        Logic.Companion.nin(this, value)
}