package core.crud.model.entity.expression.predicate.value

import core.crud.model.entity.predicate.TemporalVariable
import java.time.temporal.Temporal

class TemporalValue<T : Temporal> private constructor(override val value: T) : Value<T>, TemporalVariable {
    companion object {
        fun <T : Temporal> temporal(value: T) = TemporalValue(value)
    }
}