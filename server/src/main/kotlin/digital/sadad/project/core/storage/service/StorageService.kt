package digital.sadad.project.core.storage.service

import com.github.michaelbull.result.Result
import digital.sadad.project.core.storage.error.NotFoundError
import digital.sadad.project.core.storage.error.SaveError
import java.io.File

interface StorageService {
    suspend fun saveFile(
        fileName: String,
        fileUrl: String,
        fileBytes: ByteArray
    ): Result<Map<String, String>, SaveError>

    suspend fun getFile(fileName: String): Result<File, NotFoundError>
    suspend fun deleteFile(fileName: String): Result<String, Void>
}