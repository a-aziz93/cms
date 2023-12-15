package data.authorization

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthorizationRepositoryImpl : AuthorizationRepository, KoinComponent {

    private val service:AuthorizationService by inject()

    override fun signIn(login: String, password: String) {
        TODO("Not yet implemented")
    }
}