package core.crud.model.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class PredicateField(override val value: String) : AbstractPredicateValue<String>() {

    companion object {
        fun field(name: String) = PredicateField(name)
    }
}