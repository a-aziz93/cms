package ui.selfsignup.data.repository

interface SignUpRepository {
    fun signUp(login: String, password: String)
}