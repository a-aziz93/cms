package digital.sadad.project.cms.scansource.service

import cms.scan.model.entity.ScanEntity
import core.expression.aggregate.AggregateExpression
import core.expression.logic.extension.eq
import core.expression.projection.extension.p
import core.expression.variable.extension.f
import core.extension.repeat
import core.extension.xmlProperties
import core.serializers.LocalDateTimeSerializer
import digital.sadad.project.cms.scan.repository.ScanCRUDRepository
import digital.sadad.project.cms.scan.service.ScanService
import digital.sadad.project.cms.scanmapper.repository.ScanMapperCRUDRepository
import digital.sadad.project.cms.scanmapper.service.ScanMapperService
import digital.sadad.project.cms.scansource.repository.ScanSourceCRUDRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import kotlinx.serialization.Serializable
import org.apache.commons.io.IOUtils
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPFile
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamConstants
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration


class ScanSourceServiceImpl(
    override val crudRepository: ScanSourceCRUDRepository,
    val scanMapperCRUDRepository: ScanMapperCRUDRepository,
    val scanCRUDRepository: ScanCRUDRepository,
) : ScanSourceService {
    suspend fun fetchFtpXml(
        duration: Duration,
        fromCameraDateTime: LocalDateTime,
        fromXrayDateTime: LocalDateTime,
        fromScalesDateTime: LocalDateTime,
    ) = transactional<Unit> {
        duration
            .repeat {

                crudRepository.find().onEach { source ->

                    val cameraFtpClient = FTPClient()
                    val xrayFtpClient = FTPClient()
                    val scalesFtpClient = FTPClient()


                    try {
                        cameraFtpClient.connect(source.cameraServer, source.cameraPort)
                        cameraFtpClient.login(source.cameraUser, source.cameraPassword)
                        cameraFtpClient.enterLocalPassiveMode()
                        cameraFtpClient.changeWorkingDirectory(source.scalesDirPath)

                        xrayFtpClient.connect(source.xrayServer, source.xrayPort)
                        xrayFtpClient.login(source.xrayUser, source.xrayPassword)
                        xrayFtpClient.enterLocalPassiveMode()
                        xrayFtpClient.changeWorkingDirectory(source.scalesDirPath)

                        scalesFtpClient.connect(source.scalesServer, source.scalesPort)
                        scalesFtpClient.login(source.scalesUser, source.scalesPassword)
                        scalesFtpClient.enterLocalPassiveMode()
                        scalesFtpClient.changeWorkingDirectory(source.scalesDirPath)



                        if (cameraFtpClient.isConnected && xrayFtpClient.isConnected && scalesFtpClient.isConnected) {
                            val mapper =
                                scanMapperCRUDRepository.find(predicate = "id".f().eq(source.mapperId)).single()

                            val temporalFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd")

                            val cameraTemporalFormater =
                                DateTimeFormatter.ofPattern(source.cameraDirTemporalFormat)
                            val xrayTemporalFormater = DateTimeFormatter.ofPattern(source.xrayDirTemporalFormat)
                            val scalesTemporalFormater = DateTimeFormatter.ofPattern(source.scalesDirTemporalFormat)

                            val hasEntities = scanCRUDRepository.aggregate(AggregateExpression.count()) as Long == 0L


                            val cameraLastDateTime: LocalDateTime =
                                if (hasEntities) fromCameraDateTime else scanCRUDRepository.aggregate(
                                    "cameradatetime".p().max()
                                )


                            val cameraXMLProperties = (1..<ChronoUnit.DAYS.between(
                                cameraLastDateTime,
                                LocalDateTime.now()
                            )).fold(emptyList<Map<String, Any?>>()) { acc, day ->

                                val dateTime = cameraLastDateTime.plusDays(day)

                                cameraFtpClient.changeWorkingDirectory(
                                    "${source.cameraDirPath}/${
                                        dateTime.format(
                                            cameraTemporalFormater
                                        )
                                    }"
                                )


                                acc + ftpXMLFileProperties(
                                    cameraFtpClient,
                                    source.cameraInDirectories,
                                    source.cameraFileNamePattern,
                                    mapOf(
                                        mapper.cameraVehicleLicensePlate to String::class,
                                        mapper.cameraTrailerLicensePlate to String::class,
                                    ).filterKeys { it != null } as Map<String, KClass<*>>,
                                    setOfNotNull(
                                        mapper.cameraVehicleLicensePlateImage,
                                        mapper.cameraTrailerLicensePlateImage,
                                    ),
                                    mapper.cameraDateTime,
                                    mapper.cameraDateTimeFormat,
                                )

                            }


                            val xrayLastDateTime: LocalDateTime =
                                if (hasEntities) fromXrayDateTime else scanCRUDRepository.aggregate(
                                    "xrayadatetime".p().max()
                                )


                            val xrayXMLProperties = (1..<ChronoUnit.DAYS.between(
                                xrayLastDateTime,
                                LocalDateTime.now()
                            )).fold(emptyList<Map<String, Any?>>()) { acc, day ->

                                val dateTime = xrayLastDateTime.plusDays(day)

                                xrayFtpClient.changeWorkingDirectory(
                                    "${source.xrayDirPath}/${
                                        dateTime.format(
                                            xrayTemporalFormater
                                        )
                                    }"
                                )


                                acc + ftpXMLFileProperties(
                                    xrayFtpClient,
                                    source.xrayInDirectories,
                                    source.xrayFileNamePattern,
                                    mapOf(
                                        mapper.xrayVehicleLicensePlate to String::class,
                                        mapper.xrayCustomsCode to String::class,
                                        mapper.xrayOfficerName to String::class,
                                        mapper.xrayDriveName to String::class,
                                        mapper.xrayVehicleModel to String::class,
                                        mapper.xrayConsignerName to String::class,
                                        mapper.xrayCountryDispatch to String::class,
                                        mapper.xrayControlType to String::class,
                                        mapper.xrayControlDescription to String::class,
                                    ),
                                    setOfNotNull(
                                        mapper.xrayDriverLicenseImage,
                                        mapper.xrayVehicleСertificateImage,
                                        mapper.xraySMRImage,
                                        mapper.xrayImage,
                                    ),
                                    mapper.xrayDateTime,
                                    mapper.xrayDateTimeFormat,
                                )
                            }


                            val scalesLastDateTime: LocalDateTime =
                                if (hasEntities) fromScalesDateTime else scanCRUDRepository.aggregate(
                                    "scalesadatetime".p().max()
                                )


                            val scalesXMLProperties = (1..<ChronoUnit.DAYS.between(
                                scalesLastDateTime,
                                LocalDateTime.now()
                            )).fold(emptyList<Map<String, Any?>>()) { acc, day ->

                                val dateTime = scalesLastDateTime.plusDays(day)

                                scalesFtpClient.changeWorkingDirectory(
                                    "${source.scalesDirPath}/${
                                        dateTime.format(
                                            scalesTemporalFormater
                                        )
                                    }"
                                )

                                acc + ftpXMLFileProperties(
                                    scalesFtpClient,
                                    source.scalesInDirectories,
                                    source.scalesFileNamePattern,
                                    mapOf(
                                        mapper.scalesVehicleLicensePlate to String::class,
                                        mapper.scalesGoodWeight to Double::class,
                                        mapper.scalesTruckWeight to Double::class,
                                        mapper.scalesTotalWeight to Double::class,
                                    ),
                                    dateTimeProperty = mapper.scalesDateTime,
                                    dateTimeFormat = mapper.scalesDateTimeFormat,
                                )
                            }

                            val scanXMLProperties = cameraXMLProperties.map {
                                val licensePlate = it[mapper.cameraVehicleLicensePlate]
                                it + xrayXMLProperties.find { it[mapper.xrayVehicleLicensePlate] == licensePlate }!! +
                                        scalesXMLProperties.find { it[mapper.xrayVehicleLicensePlate] == licensePlate }!!
                            }

                            scanCRUDRepository.save(
                                scanXMLProperties.map {
                                    ScanEntity(
                                        docNumber = "${it[mapper.xrayCustomsCode]}/${
                                            temporalFormater.format(
                                                it[mapper.cameraDateTime] as LocalDateTime
                                            )
                                        }",
                                        vehicleLicensePlate = it[mapper.cameraVehicleLicensePlate].toString(),
                                        cameraVehicleLicensePlateImage = it[mapper.cameraVehicleLicensePlateImage] as ByteArray,
                                        cameraTrailerLicensePlate = it[mapper.cameraTrailerLicensePlate]?.let { it.toString() },
                                        cameraTrailerLicensePlateImage = it[mapper.cameraTrailerLicensePlateImage]?.let { it as ByteArray },
                                        cameraDateTime = it[mapper.cameraDateTime] as LocalDateTime,
                                        xrayCustomsCode = it[mapper.xrayCustomsCode].toString(),
                                        xrayOfficerName = it[mapper.xrayOfficerName].toString(),
                                        xrayDriveName = it[mapper.xrayDriveName].toString(),
                                        xrayDriverLicenseImage = it[mapper.xrayDriverLicenseImage] as ByteArray,
                                        xrayVehicleModel = it[mapper.xrayVehicleModel].toString(),
                                        xrayVehicleСertificateImage = it[mapper.xrayVehicleСertificateImage]?.let { it as ByteArray },
                                        xraySMRImage = it[mapper.xraySMRImage]?.let { it as ByteArray },
                                        xrayConsignerName = it[mapper.xrayConsignerName].toString(),
                                        xrayCountryDispatch = it[mapper.xrayCountryDispatch].toString(),
                                        xrayControlType = it[mapper.xrayControlType].toString(),
                                        xrayControlDescription = it[mapper.xrayControlDescription].toString(),
                                        xrayImage = it[mapper.xrayImage] as ByteArray,
                                        xrayDateTime = it[mapper.xrayDateTime] as LocalDateTime,
                                        scalesGoodWeight = it[mapper.scalesGoodWeight] as Double,
                                        scalesTruckWeight = it[mapper.scalesTruckWeight] as Double,
                                        scalesTotalWeight = it[mapper.scalesTotalWeight] as Double,
                                        scalesDateTime = it[mapper.scalesDateTime] as LocalDateTime,
                                    )
                                },
                                true,
                            )
                        }

                        // Close the FTP connection
                        cameraFtpClient.logout()
                        cameraFtpClient.disconnect()
                        xrayFtpClient.logout()
                        xrayFtpClient.disconnect()
                        scalesFtpClient.logout()
                        scalesFtpClient.disconnect()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        try {
                            cameraFtpClient.disconnect()
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

    companion object {
        private fun ftpXMLFileProperties(
            ftpClient: FTPClient,
            inDirectories: Boolean,
            fileNamePattern: String?,
            properties: Map<String, KClass<*>>,
            imageProperties: Set<String> = emptySet(),
            dateTimeProperty: String,
            dateTimeFormat: String,
        ): List<Map<String, Any?>> {
            val fileNameRegex = fileNamePattern?.let { Regex(it) }

            val dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat)

            return if (inDirectories) {
                ftpClient.listDirectories().map { dir ->
                    var fileList = ftpClient.listFiles(dir.name)
                        .filter { it.isFile && it.name.endsWith(".xml") }

                    fileNameRegex?.let {
                        fileList =
                            fileList.filter { !(fileNameRegex.find(it.name)?.groupValues?.get(1)?.isEmpty() ?: true) }
                    }

                    fileList.map { it.name }
                }
            } else {
                val fileList = ftpClient.listFiles()
                    .filter { it.isFile && it.name.endsWith(".xml") }

                (fileNameRegex?.let {
                    fileList
                        .groupBy {
                            fileNameRegex.find(it.name)?.groupValues?.get(1)
                        }
                        .filter { it.key != null }
                        .map { it.value.map { it.name } }
                } ?: fileList.map { listOf(it.name) })

            }.map {
                val map = it.fold(emptyMap<String, Any?>()) { acc, name ->
                    acc + ftpClient.retrieveFileStream(name).xmlProperties(properties)
                }

                val parent = it[0].replaceAfterLast("\\", "")

                map + mapOf(
                    dateTimeProperty to LocalDateTime.parse(
                        map[dateTimeProperty].toString(),
                        dateTimeFormatter
                    )
                ) + imageProperties.associate {
                    it to IOUtils.toByteArray(ftpClient.retrieveFileStream("$parent\\${map[it]}"))
                }
            }
        }
    }
}