package digital.sadad.project.core.security.service

interface LoginService<T : Any> {
    suspend fun login(username: String, password: String): T
}