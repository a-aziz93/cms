package core.error

open class HttpError(val statusCode: Int, message: String) : Error(message)

