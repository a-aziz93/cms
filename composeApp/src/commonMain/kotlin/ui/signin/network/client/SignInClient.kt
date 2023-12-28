package ui.signin.network.client

import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.annotation.Single

interface SignInClient{

}


@Single
fun injectClient(ktorfit: Ktorfit): SignInClient = ktorfit.create()