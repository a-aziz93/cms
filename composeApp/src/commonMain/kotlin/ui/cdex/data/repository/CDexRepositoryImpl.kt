package ui.cdex.data.repository

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CDexRepositoryImpl : CDexRepository, KoinComponent {

    private val service: CDexService by inject()
    
}