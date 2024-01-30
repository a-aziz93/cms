package digital.sadad.project.cms.scanmapper.service

import cms.scan.model.entity.ScanEntity
import core.expression.projection.extension.p
import core.expression.variable.extension.f
import core.extension.xmlProperties
import digital.sadad.project.cms.scan.repository.ScanCRUDRepository
import digital.sadad.project.cms.scanmapper.repository.ScanMapperCRUDRepository
import kotlinx.coroutines.flow.single
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPFile
import java.io.InputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.xml.stream.XMLEventReader
import javax.xml.stream.XMLInputFactory


class ScanMapperServiceImpl(
    override val crudRepository: ScanMapperCRUDRepository,
    val scanCRUDRepository: ScanCRUDRepository,
) : ScanMapperService {
    override suspend fun saveFtpXmlToScan(
        mapperId: Long,
        licensePlateFtpClient: FTPClient,
        xrayFtpClient: FTPClient,
        scalesFtpClient: FTPClient
    ): ScanEntity {

        val mapper = crudRepository.find(predicate = "id".f().eq(mapperId)).single()

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

    }
}


















        licensePlateInputStream.fold(emptyMap<String, Any?>(), { acc, inputStream ->

            inputStream.xmlProperties(
                mapper.carLicensePlate,
                mapper.trailerLicensePlate,
            )

        })
        xrayInputStream.fold(emptyMap<String, Any?>(), { acc, inputStream -> emptyMap() })
        scalesInputStream.fold(emptyMap<String, Any?>(), { acc, inputStream -> emptyMap() })

        ScanEntity(
            docNumber = "",
            customsCode =,
            officerName =,
            driveName =,
            driverLicenseImage =,
            carModel =,
            carСertificateImage =,
            carLicensePlate =,
            carLicansePlateImage =,
            trailerLicensePlate =,
            smrImage =,
            consignerName =,
            countryDispatch =,
            goodWeight =,
            truckWeight =,
            totalWeight =,
            controlType =,
            controlDescription =,
            xrayImage =,
            dateTime =,
        )
    }
}