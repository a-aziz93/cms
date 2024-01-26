package core.expression.text

import core.expression.variable.StringVariable
import core.expression.text.TextExpressionType.*
import core.expression.variable.CharVariable
import core.expression.variable.Variable
import core.expression.variable.extension.v

class TextExpression(
    val type: TextExpressionType,
    vararg val values: Variable,
) : StringVariable {

    companion object {
        fun ascii(value: StringVariable) = TextExpression(ASCII, value)
        fun length(value: StringVariable) = TextExpression(LENGTH, value)
        fun lower(value: StringVariable) = TextExpression(LOWER, value)
        fun upper(value: StringVariable) = TextExpression(UPPER, value)
        fun substr(value: StringVariable, startIndex: Long, endIndex: Long? = null) =
            TextExpression(SUBSTR, value, *listOfNotNull(startIndex, endIndex).map { it.v() }.toTypedArray())

        fun replace(value: StringVariable, pattern: StringVariable, replacement: StringVariable) =
            TextExpression(REPLACE, value, pattern, replacement)

        fun reverse(value: StringVariable) = TextExpression(REVERSE, value)
        fun trim(value: StringVariable, vararg trimValues: CharVariable) = TextExpression(TRIM, value, *trimValues)
        fun lTrim(value: StringVariable, vararg trimValues: CharVariable) = TextExpression(LTRIM, value, *trimValues)
        fun rTrim(value: StringVariable, vararg trimValues: CharVariable) = TextExpression(RTRIM, value, *trimValues)
        fun lPad(value: StringVariable, count: Long, vararg padValues: CharVariable) =
            TextExpression(LPAD, value, count.v(), *padValues)

        fun rPad(value: StringVariable, count: Long, vararg padValues: CharVariable) =
            TextExpression(RPAD, value, count.v(), *padValues)

        fun left(value: StringVariable, count: Long) = TextExpression(LEFT, value, count.v())
        fun right(value: StringVariable, count: Long) = TextExpression(RIGHT, value, count.v())
        fun replicate(value: StringVariable, count: Long) = TextExpression(REPLICATE, value, count.v())
        fun indexOf(value: StringVariable, pattern: StringVariable) = TextExpression(INDEXOF, value, pattern)
        fun space(count: Long) = TextExpression(SPACE, count.v())
        fun split(value: StringVariable, separator: CharVariable) = TextExpression(SPLIT, value, separator)
    }
}