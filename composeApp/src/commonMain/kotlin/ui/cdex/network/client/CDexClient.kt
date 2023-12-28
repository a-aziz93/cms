package ui.cdex.network.client

import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.annotation.Single

interface CDexClient{

}

@Single
fun injectClient(ktorfit: Ktorfit): CDexClient = ktorfit.create()