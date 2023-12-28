package ui.cdex.data.repository

import ui.cms.network.client.CMSClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.cdex.network.client.CDexClient

class CDexService: KoinComponent {
  private val service: CDexClient by inject()

  suspend fun getAll(){

  }
}
   