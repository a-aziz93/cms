package core.expression.value

import core.crud.repository.model.expression.variable.TemporalVariable
import kotlinx.serialization.Serializable
import java.time.temporal.Temporal

@Serializable
class TemporalValue<T : Temporal> private constructor(override val value: T) : Value<T>, TemporalVariable {
    companion object {
        fun <T : Temporal> temporal(value: T) = TemporalValue(value)
    }
}