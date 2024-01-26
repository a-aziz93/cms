package core.expression.text.extension

import core.expression.text.TextExpression
import core.expression.text.TextExpressionType
import core.expression.variable.CharVariable
import core.expression.variable.StringVariable
import core.expression.variable.extension.v

fun String.ascii() = this.v().ascii()
fun String.length() = this.v().length()
fun String.lower() = this.v().lower()
fun String.upper() = this.v().upper()
fun String.substr(startIndex: Long, endIndex: Long? = null) = this.v().substr(startIndex, endIndex)

fun String.replace(pattern: StringVariable, replacement: StringVariable) = this.v().replace(pattern, replacement)

fun String.reverse() = this.v().reverse()
fun String.trim(vararg trimValues: CharVariable) =
    this.v().trim(*trimValues)

fun String.lTrim(vararg trimValues: CharVariable) =
    this.v().lTrim(*trimValues)

fun String.rTrim(vararg trimValues: CharVariable) =
    this.v().rTrim(*trimValues)

fun String.lPad(count: Long, vararg padValues: CharVariable) =
    this.v().lPad(count, *padValues)

fun String.rPad(count: Long, vararg padValues: CharVariable) =
    this.v().rPad(count, *padValues)

fun String.left(count: Long) = this.v().left(count)
fun String.right(count: Long) = this.v().right(count)
fun String.replicate(count: Long) = this.v().replicate(count)
fun String.indexOf(pattern: StringVariable) =
    this.v().indexOf(pattern)

fun Long.space() = TextExpression.space(this)

fun String.split(separator: CharVariable) =
    this.v().split(separator)


fun String.replace(pattern: String, replacement: String) = this.replace(pattern.v(), replacement.v())

fun String.trim(vararg trimValues: Char) =
    this.trim(*trimValues.map { it.v() }.toTypedArray())

fun String.lTrim(vararg trimValues: Char) =
    this.lTrim(*trimValues.map { it.v() }.toTypedArray())

fun String.rTrim(vararg trimValues: Char) =
    this.rTrim(*trimValues.map { it.v() }.toTypedArray())

fun String.lPad(count: Long, vararg padValues: Char) =
    this.lPad(count, *padValues.map { it.v() }.toTypedArray())

fun String.rPad(count: Long, vararg padValues: Char) =
    this.rPad(count, *padValues.map { it.v() }.toTypedArray())

fun String.indexOf(pattern: String) =
    this.indexOf(pattern.v())

fun String.split(separator: Char) =
    this.split(separator.v())