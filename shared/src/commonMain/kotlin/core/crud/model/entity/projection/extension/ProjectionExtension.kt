package core.crud.model.entity.projection.extension

import core.crud.model.entity.projection.Projection

fun String.p(distinct: Boolean? = null) = Projection(this, distinct)