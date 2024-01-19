package core.crud.model.entity.expression

import core.crud.model.entity.expression.logic.Logic

interface CharVariable : Variable {
    fun eq(value: CharVariable) =
        Logic.Companion.eq(this, value)

    fun neq(value: CharVariable) =
        Logic.Companion.neq(this, value)

    fun ln(value: CharCollectionVariable) =
        Logic.Companion.ln(this, value)

    fun nin(value: CharCollectionVariable) =
        Logic.Companion.nin(this, value)
}