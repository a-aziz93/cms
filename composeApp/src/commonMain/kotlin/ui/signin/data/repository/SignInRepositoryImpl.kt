package ui.signin.data.repository

import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Single
class SignInRepositoryImpl(private val signInService: SignInService) : SignInRepository {

    override fun signIn(login: String, password: String) {
        TODO("Not yet implemented")
    }
}