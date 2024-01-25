package core.expression.projection.extension

import core.crud.repository.model.expression.projection.Projection.Companion.projection

fun String.p(distinct: Boolean? = null) = projection(this, distinct)
