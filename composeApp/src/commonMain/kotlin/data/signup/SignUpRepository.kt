package data.signup

interface SignUpRepository {
    fun signUp(login:String, password:String)
}