package ui.queue.network.client

import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.annotation.Single

interface QueueClient{

}

@Single
fun injectClient(ktorfit: Ktorfit): QueueClient = ktorfit.create()