package ui.cdox.data.repository

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CDoxRepositoryImpl : CDoxRepository, KoinComponent {

    private val service: CDoxService by inject()
    
}