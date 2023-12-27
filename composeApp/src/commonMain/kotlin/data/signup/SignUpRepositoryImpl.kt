package data.signup

import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Single
class SignUpRepositoryImpl(private val service: SignUpServiceImpl) : SignUpRepository {

    override fun signUp(login: String, password: String) {
        TODO("Not yet implemented")
    }
}