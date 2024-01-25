package core.expression.variable

interface Variable {

    //TODO
    @Suppress("UNUSED")
    fun <D : Any> convert(converter: (BooleanVariable) -> D): D {
        val variables = mutableListOf<Triple<Variable, Int, D?>>(Triple(this, 0, null))

        while (variables.size > 0) {

        }

        return variables.first().third!!
    }
}