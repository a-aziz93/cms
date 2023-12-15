package data.registration

import core.network.client.registration.RegistrationClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

  class RegistrationService: KoinComponent {
  private val service:RegistrationClient by inject()
  
  suspend fun getAll(){
    
  }
}
   