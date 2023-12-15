package core.storage

enum class StorageKeys {
                       TOKEN,
    LOGIN_INFO,
    
    IS_DARK_THEME,
    
    LANGUAGE;
    
    val key get() = this.name
}