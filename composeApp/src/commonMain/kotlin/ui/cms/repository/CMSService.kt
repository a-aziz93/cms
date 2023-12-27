package ui.cms.repository

import ui.cms.network.client.CMSClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

  class CMSService: KoinComponent {
  private val service: CMSClient by inject()
  
  suspend fun getAll(){
    
  }
}
   