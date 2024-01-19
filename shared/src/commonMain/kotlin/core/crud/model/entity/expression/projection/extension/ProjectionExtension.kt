package core.crud.model.entity.expression.projection.extension

import core.crud.model.entity.projection.Projection

fun String.p(distinct: Boolean? = null) = Projection(this, distinct)