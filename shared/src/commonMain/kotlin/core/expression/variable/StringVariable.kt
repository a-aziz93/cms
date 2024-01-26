package core.expression.variable

import core.expression.logic.LogicExpression
import core.expression.text.TextExpression
import core.expression.text.TextExpressionType
import core.expression.variable.extension.v
import core.expression.text.TextExpression.Companion.ascii
import core.expression.text.TextExpression.Companion.length
import core.expression.text.TextExpression.Companion.lower
import core.expression.text.TextExpression.Companion.upper
import core.expression.text.TextExpression.Companion.substr
import core.expression.text.TextExpression.Companion.replace
import core.expression.text.TextExpression.Companion.reverse
import core.expression.text.TextExpression.Companion.trim
import core.expression.text.TextExpression.Companion.lTrim
import core.expression.text.TextExpression.Companion.rTrim
import core.expression.text.TextExpression.Companion.lPad
import core.expression.text.TextExpression.Companion.rPad
import core.expression.text.TextExpression.Companion.left
import core.expression.text.TextExpression.Companion.right
import core.expression.text.TextExpression.Companion.replicate
import core.expression.text.TextExpression.Companion.indexOf
import core.expression.text.TextExpression.Companion.split
import core.expression.logic.LogicExpression.Companion.eq
import core.expression.logic.LogicExpression.Companion.neq
import core.expression.logic.LogicExpression.Companion.like
import core.expression.logic.LogicExpression.Companion.`in`
import core.expression.logic.LogicExpression.Companion.nin

interface StringVariable : Variable {
    fun ascii() = ascii(this)
    fun length() = length(this)
    fun lower() = lower(this)
    fun upper() = upper(this)
    fun substr(startIndex: Long, endIndex: Long? = null) = substr(this, startIndex, endIndex)

    fun replace(pattern: StringVariable, replacement: StringVariable) = replace(this, pattern, replacement)

    fun reverse() = reverse(this)
    fun trim(vararg values: CharVariable) = trim(this)
    fun lTrim(vararg values: CharVariable) = lTrim(this)

    fun rTrim(vararg values: CharVariable) = rTrim(this)

    fun lPad(count: Long, vararg padValues: CharVariable) = lPad(this, count, *padValues)

    fun rPad(count: Long, vararg padValues: CharVariable) = rPad(this, count, *padValues)

    fun left(count: Long) = left(this, count)
    fun right(count: Long) = right(this, count)
    fun replicate(count: Long) = replicate(this, count)
    fun indexOf(pattern: StringVariable) = indexOf(this, pattern)

    fun split(separator: CharVariable) = split(this, separator)


    fun like(value: StringVariable) =
        like(this, value)

    fun `in`(value: StringCollectionVariable) =
        `in`(this, value)

    fun nin(value: StringCollectionVariable) =
        nin(this, value)
}