package data.cms

import core.network.client.cms.CMSClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

  class CMSService: KoinComponent {
  private val service:CMSClient by inject()
  
  suspend fun getAll(){
    
  }
}
   