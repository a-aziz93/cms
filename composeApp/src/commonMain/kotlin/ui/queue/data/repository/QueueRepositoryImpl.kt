package ui.queue.data.repository

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class QueueRepositoryImpl : QueueRepository, KoinComponent {

    private val service: QueueService by inject()
    
}