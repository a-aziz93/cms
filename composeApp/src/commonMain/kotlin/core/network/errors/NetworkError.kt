package core.network.errors



data class NetworkError(val code: NetworkErrorCode,  val message:String?=null)