package core.crud.model.entity.expression.predicate

import kotlinx.serialization.Serializable

@Serializable
enum class PredicateExpression {
    AND,
    OR,
    NOT,
    XOR,
    EQUALS,
    NOT_EQUALS,
    GREATER_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN,
    LESS_THAN_EQUAL,
    LIKE,
    BETWEEN,
    IN,
    NIN,
}