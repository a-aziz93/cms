package core.network.client.signup

import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.annotation.Single

interface SignUpClient{

}


@Single
fun injectClient(ktorfit: Ktorfit): SignUpClient = ktorfit.create()