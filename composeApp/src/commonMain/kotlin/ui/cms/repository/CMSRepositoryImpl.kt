package ui.cms.repository

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CMSRepositoryImpl : CMSRepository, KoinComponent {

    private val service: CMSService by inject()
    
}