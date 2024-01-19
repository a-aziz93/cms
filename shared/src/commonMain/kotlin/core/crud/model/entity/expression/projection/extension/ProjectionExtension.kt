package core.crud.model.entity.expression.projection.extension

import core.crud.model.entity.expression.projection.Projection.Companion.projection

fun String.p(distinct: Boolean? = null) = projection(this, distinct)
