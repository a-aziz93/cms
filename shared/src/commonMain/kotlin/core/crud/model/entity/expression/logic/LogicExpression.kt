package core.crud.model.entity.expression.logic

import kotlinx.serialization.Serializable

@Serializable
enum class LogicExpression {
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