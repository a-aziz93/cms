package ui.selfsignup.data.repository

import core.network.client.signup.SignUpClient
import org.koin.core.annotation.Single

@Single
class SignUpServiceImpl(private val signUpClient: SignUpClient): SignUpService {

}
   