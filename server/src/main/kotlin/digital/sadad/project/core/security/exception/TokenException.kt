package digital.sadad.project.core.security.exception

sealed class TokenException(message: String) : RuntimeException(message) {
    class InvalidTokenException(message: String) : TokenException(message)
}