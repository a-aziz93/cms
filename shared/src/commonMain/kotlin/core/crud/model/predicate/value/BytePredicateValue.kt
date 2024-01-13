package core.crud.model.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class BytePredicateValue private constructor(override val value: Byte) : PredicateValue<Byte> {
    companion object {
        fun byte(value: Byte) = BytePredicateValue(value)
    }
}
