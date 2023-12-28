package ui.settings.data.repository

interface SettingsRepository {
    fun signUp(login: String, password: String)
}