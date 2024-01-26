package core.expression.temporal.extension

import core.expression.temporal.TemporalExpression
import core.expression.temporal.TemporalExpressionType
import core.expression.variable.StringVariable
import core.expression.variable.TemporalVariable
import core.expression.variable.extension.v
import java.time.temporal.Temporal

fun Temporal.time() = this.v().time()

fun Temporal.date() = this.v().date()

fun Temporal.datetime() = this.v().datetime()

fun Temporal.datetimeOffset() = this.v().datetimeOffset()

fun Temporal.format(format: StringVariable) = this.v().format(format)

fun Temporal.format(format: String) = this.v().format(format.v())
