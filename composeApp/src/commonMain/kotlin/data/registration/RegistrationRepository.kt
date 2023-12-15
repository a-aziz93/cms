package data.registration

interface RegistrationRepository {
    fun signUp(login:String, password:String)
}