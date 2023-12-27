package data.signup

import core.network.client.signup.SignUpClient
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single
class SignUpServiceImpl(private val signUpClient: SignUpClient): SignUpService {
  

}
   