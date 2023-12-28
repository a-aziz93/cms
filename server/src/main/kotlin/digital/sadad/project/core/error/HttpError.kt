package digital.sadad.project.core.error


open class HttpError(val statusCode: Int, message: String) : IOError(message)

