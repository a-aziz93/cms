package core.crud.model.entity.expression.predicate.value

import core.crud.model.entity.predicate.*
import kotlinx.serialization.Serializable

@Serializable
class Field(override val value: String) : Value<String>,
    core.crud.model.entity.expression.predicate.BooleanVariable,
    core.crud.model.entity.expression.predicate.NumberVariable,
    StringVariable,
    TemporalVariable {
    companion object {
        fun field(name: String) = Field(name)
    }
}