package data.registration

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegistrationRepositoryImpl : RegistrationRepository, KoinComponent {

    private val service:RegistrationService by inject()

    override fun signUp(login: String, password: String) {
        TODO("Not yet implemented")
    }
}