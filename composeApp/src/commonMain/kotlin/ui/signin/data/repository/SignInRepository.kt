package ui.signin.data.repository

interface SignInRepository {
    fun signIn(login:String, password:String)
}