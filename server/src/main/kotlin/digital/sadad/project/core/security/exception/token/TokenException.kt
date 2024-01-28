package digital.sadad.project.core.security.exception.token

sealed class TokenException(message: String) : RuntimeException(message) {
    class InvalidTokenException(message: String) : TokenException(message)
}