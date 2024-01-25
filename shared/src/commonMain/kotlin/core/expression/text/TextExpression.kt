package core.expression.text

import core.expression.variable.StringVariable
import core.expression.text.TextExpressionType.*

class TextExpression(
    val type: TextExpressionType,
    vararg val values: StringVariable,
) : StringVariable {

    companion object {
        fun concat(vararg values: StringVariable) = TextExpression(CONCAT, *values)
    }
}