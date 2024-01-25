package core.expression.arithmetic

import kotlinx.serialization.Serializable

@Serializable
enum class ArithmeticExpressionType {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    MOD,
    POWER,
    SQUARE,
}