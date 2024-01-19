package core.crud.model.entity.expression.arithmetic.extension

import core.crud.model.entity.expression.NumberVariable
import core.crud.model.entity.expression.value.extension.v

fun Number.add(vararg values: NumberVariable) = this.v().add(*values)

fun Number.subtract(vararg values: NumberVariable) = this.v().subtract(*values)

fun Number.multiply(vararg values: NumberVariable) = this.v().multiply(*values)

fun Number.divide(vararg values: NumberVariable) = this.v().divide(*values)

fun Number.mod(
    value: NumberVariable,
) = this.v().mod(value)

fun Number.power(
    value: NumberVariable,
) = this.v().power(value)

fun Number.square() = this.v().square()

fun Number.add(vararg values: Number) = this.v().add(*values.map { it.v() }.toTypedArray())

fun Number.subtract(vararg values: Number) = this.v().subtract(*values.map { it.v() }.toTypedArray())

fun Number.multiply(vararg values: Number) = this.v().multiply(*values.map { it.v() }.toTypedArray())

fun Number.divide(vararg values: Number) = this.v().divide(*values.map { it.v() }.toTypedArray())

fun Number.mod(
    value: Number,
) = this.v().mod(value.v())

fun Number.power(
    value: Number,
) = this.v().power(value.v())