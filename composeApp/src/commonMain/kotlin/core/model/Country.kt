package core.model

data class Country(
    var alpha2Code: String,
    val phoneCode: String = "",
    val name: String = "",
    val flagResID: Int = 0
) {
    override fun toString(): String = name
}