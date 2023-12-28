package ui.cms.network.client

import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.annotation.Single

interface CMSClient{

}

@Single
fun injectClient(ktorfit: Ktorfit): CMSClient = ktorfit.create()