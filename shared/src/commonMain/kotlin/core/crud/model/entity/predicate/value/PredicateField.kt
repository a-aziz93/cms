package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class PredicateField(override val value: String) : PredicateValue<String> {

    companion object {
        fun field(name: String) = PredicateField(name)
    }
}