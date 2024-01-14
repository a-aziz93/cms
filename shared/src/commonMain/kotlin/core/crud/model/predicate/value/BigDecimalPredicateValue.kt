package core.crud.model.predicate.value

import core.serializers.BigDecimalJson
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
class BigDecimalPredicateValue private constructor(override val value: BigDecimalJson) : PredicateValue<BigDecimal> {
    companion object {
        fun bigDecimal(value: BigDecimal) = BigDecimalPredicateValue(value)
    }
}
