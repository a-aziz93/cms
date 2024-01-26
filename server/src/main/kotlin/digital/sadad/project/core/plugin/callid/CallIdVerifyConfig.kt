package digital.sadad.project.core.plugin.callid

data class CallIdVerifyConfig(
    val dictionary: String,
    val reject: Boolean = false,
)