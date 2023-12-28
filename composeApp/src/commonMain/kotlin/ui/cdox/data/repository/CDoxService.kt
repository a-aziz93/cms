package ui.cdox.data.repository

import ui.cms.network.client.CMSClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CDoxService: KoinComponent {
  private val service: CMSClient by inject()

  suspend fun getAll(){

  }
}
   