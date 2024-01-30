package digital.sadad.project.cms.scansource.service

import cms.scan.model.entity.ScanEntity
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import core.expression.logic.extension.eq
import core.expression.projection.extension.p
import core.extension.repeat
import core.extension.xmlProperties
import digital.sadad.project.cms.scan.repository.ScanCRUDRepository
import digital.sadad.project.cms.scan.service.ScanService
import digital.sadad.project.cms.scanmapper.repository.ScanMapperCRUDRepository
import digital.sadad.project.cms.scanmapper.service.ScanMapperService
import digital.sadad.project.cms.scansource.repository.ScanSourceCRUDRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPFile
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamConstants
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration


class ScanSourceServiceImpl(
    override val crudRepository: ScanSourceCRUDRepository,
    val scanMapperService: ScanMapperService,
) : ScanSourceService {
    suspend fun fetchFtpXml() = transactional<Unit> {
        5.toDuration(DurationUnit.MINUTES)
            .repeat {

                crudRepository.find().onEach { source ->

                    val licensePlateFtpClient = FTPClient()
                    val xrayFtpClient = FTPClient()
                    val scalesFtpClient = FTPClient()


                    try {
                        licensePlateFtpClient.connect(source.licensePlateServer, source.licensePlatePort)
                        licensePlateFtpClient.login(source.licensePlateUser, source.licensePlatePassword)
                        licensePlateFtpClient.enterLocalPassiveMode()
                        licensePlateFtpClient.changeWorkingDirectory(source.scalesDirPath)

                        xrayFtpClient.connect(source.xrayServer, source.xrayPort)
                        xrayFtpClient.login(source.xrayUser, source.xrayPassword)
                        xrayFtpClient.enterLocalPassiveMode()
                        xrayFtpClient.changeWorkingDirectory(source.scalesDirPath)

                        scalesFtpClient.connect(source.scalesServer, source.scalesPort)
                        scalesFtpClient.login(source.scalesUser, source.scalesPassword)
                        scalesFtpClient.enterLocalPassiveMode()
                        scalesFtpClient.changeWorkingDirectory(source.scalesDirPath)



                        if (licensePlateFtpClient.isConnected && xrayFtpClient.isConnected && scalesFtpClient.isConnected) {

                            val licensePlateTemporalFormater =
                                DateTimeFormatter.ofPattern(source.licensePlateTemporalFormat)
                            val xrayTemporalFormater = DateTimeFormatter.ofPattern(source.xrayTemporalFormat)
                            val scalesTemporalFormater = DateTimeFormatter.ofPattern(source.scalesTemporalFormat)

                            val lastDateTime: LocalDateTime = scanCRUDRepository.aggregate("datetime".p().max())

                            for (i in 1..<ChronoUnit.DAYS.between(lastDateTime, LocalDateTime.now())) {
                                val dateTime = lastDateTime.plusDays(i)

                                licensePlateFtpClient.changeWorkingDirectory(
                                    "${source.licensePlateDirPath}/${
                                        dateTime.format(
                                            licensePlateTemporalFormater
                                        )
                                    }"
                                )

                                xrayFtpClient.changeWorkingDirectory(
                                    "${source.xrayDirPath}/${
                                        dateTime.format(
                                            xrayTemporalFormater
                                        )
                                    }"
                                )
                                scalesFtpClient.changeWorkingDirectory(
                                    "${source.scalesDirPath}/${
                                        dateTime.format(
                                            scalesTemporalFormater
                                        )
                                    }"
                                )

                                val licensePlates =
                                    if (source.licensePlateInDirectories)
                                        licensePlateFtpClient.listDirectories().map {
                                            licensePlateFtpClient.listFiles(it.name)
                                                .filter { it.type == FTPFile.FILE_TYPE && it.name.endsWith(".xml") }
                                                .map { licensePlateFtpClient.retrieveFileStream(it.name) }
                                        }
                                    else
                                        licensePlateFtpClient.listFiles()
                                            .filter { it.type == FTPFile.FILE_TYPE && it.name.endsWith(".xml") }
                                            .map { licensePlateFtpClient.retrieveFileStream(it.name) }.groupBy {
                                                it.xmlProperties()
                                            }

                                if (source.xrayInDirectories) {
                                    xrayFtpClient.listDirectories()
                                        .map { licensePlateFtpClient.retrieveFileStream(it.name) }
                                }
                                if (source.scalesInDirectories) {
                                    scalesFtpClient.listDirectories()
                                }
//                                scanCRUDRepository.save(
//                                    listOf(scanMapperService.xmlToScanEntity(
//                                        it.mapperId,
//                                        licensePlateFtpClient.listFiles()
//                                            .filter { it.type == FTPFile.FILE_TYPE && it.name.endsWith(".xml") }
//                                            .map { licensePlateFtpClient.retrieveFileStream(it.name) },
//                                        xrayFtpClient.listFiles()
//                                            .filter { it.type == FTPFile.FILE_TYPE && it.name.endsWith(".xml") }
//                                            .map { xrayFtpClient.retrieveFileStream(it.name) },
//                                        scalesFtpClient.listFiles()
//                                            .filter { it.type == FTPFile.FILE_TYPE && it.name.endsWith(".xml") }
//                                            .map { scalesFtpClient.retrieveFileStream(it.name) },
//                                    )
//                                    ),
//                                    true,
//                                )
                            }
                        }

                        // Close the FTP connection
                        licensePlateFtpClient.logout()
                        licensePlateFtpClient.disconnect()
                        xrayFtpClient.logout()
                        xrayFtpClient.disconnect()
                        scalesFtpClient.logout()
                        scalesFtpClient.disconnect()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        try {
                            licensePlateFtpClient.disconnect()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        try {
                            xrayFtpClient.disconnect()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        try {
                            scalesFtpClient.disconnect()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
    }
}