package core.crud.model.entity.predicate.value

import kotlinx.serialization.Serializable

@Serializable
class BytePredicateValue private constructor(override val value: Byte) : PredicateValue<Byte> {
    companion object {
        fun byte(value: Byte) = BytePredicateValue(value)
    }
}
