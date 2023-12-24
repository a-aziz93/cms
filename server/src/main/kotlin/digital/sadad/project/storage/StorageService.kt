package digital.sadad.project.storage

import digital.sadad.project.storage.error.StorageError
import java.io.File

interface StorageService {
    suspend fun saveFile(
        fileName: String,
        fileUrl: String,
        fileBytes: ByteArray
    ): Result<Map<String, String>, StorageError>

    suspend fun getFile(fileName: String): Result<File, StorageError>
    suspend fun deleteFile(fileName: String): Result<String, StorageError>
}