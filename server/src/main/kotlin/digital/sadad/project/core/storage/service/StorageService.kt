package digital.sadad.project.core.storage.service

import digital.sadad.project.core.storage.error.StorageError
import java.io.File
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

interface StorageService {
    suspend fun saveFile(
        fileName: String,
        fileUrl: String,
        fileBytes: ByteArray
    ): Result<Map<String, String>, StorageError>

    suspend fun getFile(fileName: String): Result<File, StorageError>
    suspend fun deleteFile(fileName: String): Result<String, StorageError>
}