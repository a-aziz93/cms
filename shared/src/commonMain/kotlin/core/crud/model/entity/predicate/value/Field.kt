package core.crud.model.entity.predicate.value

import core.crud.model.entity.predicate.*
import kotlinx.serialization.Serializable

@Serializable
class Field(override val value: String) : Value<String>,
    BooleanVariable,
    NumberVariable,
    StringVariable,
    TemporalVariable {
    companion object {
        fun field(name: String) = Field(name)
    }
}