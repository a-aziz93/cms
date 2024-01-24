package digital.sadad.project.core.service.security

interface LoginService<T : Any> {
    suspend fun login(username: String, password: String): T
}