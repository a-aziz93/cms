package core.crud.model.predicate.value

import core.serializers.BigIntegerJson
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
class BigIntegerPredicateValue private constructor(override val value: BigIntegerJson) : PredicateValue<BigInteger> {
    companion object {
        fun bigInteger(value: BigInteger) = BigIntegerPredicateValue(value)
    }
}
