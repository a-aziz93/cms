package core.crud.model.predicate.operation

import kotlinx.serialization.Serializable

@Serializable
enum class CompareOperation : PredicateOperation {
    EQUALS,
    NOT_EQUALS,
    GREATER_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN,
    LESS_THAN_EQUAL,
    BETWEEN,
    LIKE,
    IN,
}