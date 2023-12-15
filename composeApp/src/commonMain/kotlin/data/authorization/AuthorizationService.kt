package data.authorization

import core.network.client.authorization.AuthorizationClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

  class AuthorizationService: KoinComponent {
  private val service:AuthorizationClient by inject()
  
  suspend fun getAll(){
    
  }
}
   