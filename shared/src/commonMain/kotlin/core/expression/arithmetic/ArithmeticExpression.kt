package core.expression.arithmetic

import kotlinx.serialization.Serializable

@Serializable
enum class ArithmeticExpression {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    MOD,
    POWER,
    SQUARE,
}