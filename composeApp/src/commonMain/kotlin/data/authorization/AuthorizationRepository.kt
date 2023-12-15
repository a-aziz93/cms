package data.authorization

interface AuthorizationRepository {
    fun signIn(login:String, password:String)
}