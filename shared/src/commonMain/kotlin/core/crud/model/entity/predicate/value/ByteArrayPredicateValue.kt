package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class ByteArrayPredicateValue private constructor(override val value: ByteArray) : PredicateValue<ByteArray> {
    companion object {
        fun byteArray(value: ByteArray) = ByteArrayPredicateValue(value)
    }
}
