package digital.sadad.project.core.storage.service

import digital.sadad.project.core.config.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.two.KotlinLogging
import org.koin.core.annotation.Singleton
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDateTime
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import digital.sadad.project.core.config.model.StorageConfig
import core.error.BadRequest
import core.error.NotFound
import org.koin.core.annotation.Single
import java.io.IOError

private val logger = KotlinLogging.logger {}

/**
 * Storage Service to manage our files
 * @property appConfig AppConfig Configuration of our service
 */
@Single
class StorageServiceImpl(
    private val appConfig: AppConfig,
    private val storageConfig: StorageConfig? = appConfig.config.storage,
) : StorageService {

    init {
        if (storageConfig?.uploadDir != null) {
            logger.debug { " Starting Storage Service in ${storageConfig.uploadDir}" }
            // Create upload directory if not exists (or ignore if exists)
            // and clean if dev
            Files.createDirectories(Path.of(storageConfig.uploadDir))
            if (appConfig.env == "dev") {
                logger.debug { "Cleaning storage directory in ${storageConfig.uploadDir}" }
                File(storageConfig.uploadDir).listFiles()?.forEach { it.delete() }
            }
        }
    }

    /**
     * Saves a file in our storage
     * @param fileName String Name of the file
     * @param fileUrl String URL of the file
     * @param fileBytes ByteArray Bytes of the file
     * @return Result<Map<String, String>, StorageError> Map if Ok, StorageError if not
     */
    override suspend fun saveFile(
        fileName: String,
        fileUrl: String,
        fileBytes: ByteArray
    ): Result<Map<String, String>, BadRequest> =
        withContext(Dispatchers.IO) {
            if (storageConfig?.uploadDir == null) {
                throw NullPointerException("storageConfig.uploadDir")
            }

            logger.debug { "Saving file in: $fileName" }
            return@withContext try {
                File("${storageConfig.uploadDir}/$fileName").writeBytes(fileBytes)
                Ok(
                    mapOf(
                        "fileName" to fileName,
                        "createdAt" to LocalDateTime.now().toString(),
                        "size" to fileBytes.size.toString(),
                        "url" to fileUrl,
                    )
                )
            } catch (e: Exception) {
                Err(BadRequest("Error saving file: $fileName"))
            }
        }

    /**
     * Retrieves a file from our storage
     * @param fileName String Name of the file
     * @return Result<File, StorageError> File if Ok, StorageError if not
     */
    override suspend fun getFile(fileName: String): Result<File, NotFound> = withContext(Dispatchers.IO) {
        if (storageConfig?.uploadDir == null) {
            throw NullPointerException("storageConfig.uploadDir")
        }

        logger.debug { "Get file: $fileName" }
        return@withContext if (!File("${storageConfig.uploadDir}/$fileName").exists()) {
            Err(NotFound("File Not Found in storage: $fileName"))
        } else {
            Ok(File("${storageConfig.uploadDir}/$fileName"))
        }
    }

    /**
     * Deletes a file from our storage
     * @param fileName String Name of the file
     * @return Result<String, StorageError> String if Ok, StorageError if not
     */
    override suspend fun deleteFile(fileName: String): Result<String, IOError> = withContext(Dispatchers.IO) {
        if (storageConfig?.uploadDir == null) {
            throw NullPointerException("storageConfig.uploadDir")
        }

        logger.debug { "Remove file: $fileName" }
        Files.deleteIfExists(Path.of("${storageConfig.uploadDir}/$fileName"))
        Ok(fileName)
    }

}