package ui.settings.data.repository

import org.koin.core.annotation.Single

@Single
class SettingsRepositoryImpl(private val service: SettingsServiceImpl) : SettingsRepository {

    override fun signUp(login: String, password: String) {
        TODO("Not yet implemented")
    }
}