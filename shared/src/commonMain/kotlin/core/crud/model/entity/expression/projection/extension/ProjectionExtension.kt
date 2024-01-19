package core.crud.model.entity.expression.projection.extension

import core.crud.model.entity.expression.extension.f
import core.crud.model.entity.expression.projection.Projection.Companion.projection
import core.crud.model.entity.expression.value.FieldValue

fun FieldValue.p(distinct: Boolean? = null) = projection(this, distinct)
fun String.p(distinct: Boolean? = null) = this.f().p(distinct)
