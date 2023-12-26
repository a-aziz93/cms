package digital.sadad.project.core.storage.error

/**
 * Storage Errors
 */
sealed class StorageError(val message: String) {
    class NotFound(message: String) : StorageError(message)
    class BadRequest(message: String) : StorageError(message)
}