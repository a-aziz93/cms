package ui.queue.data.repository

import ui.cms.network.client.CMSClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.queue.network.client.QueueClient

class QueueService: KoinComponent {
  private val service: QueueClient by inject()

  suspend fun getAll(){

  }
}
   