package digital.sadad.project.cms.scansource.service

import cms.scan.model.entity.ScanEntity
import core.expression.logic.extension.eq
import core.extension.repeat
import digital.sadad.project.cms.scan.repository.ScanCRUDRepository
import digital.sadad.project.cms.scanmapper.repository.ScanMapperCRUDRepository
import digital.sadad.project.cms.scansource.repository.ScanSourceCRUDRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPFile
import java.io.IOException
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamConstants
import kotlin.time.Duration


class ScanSourceServiceImpl(
    override val crudRepository: ScanSourceCRUDRepository,
    val scanCRUDRepository: ScanCRUDRepository,
    val scanMapperCRUDRepository: ScanMapperCRUDRepository,
) : ScanSourceService {
    suspend fun fetchFtpXml(): Unit = crudRepository.transactional {
        Duration.parse("5m").repeat {

            crudRepository.find().onEach {
                val mapper = scanMapperCRUDRepository.find(predicate = "id".eq(it.mapperId)).single()

                val licensePlateFtpClient = FTPClient()
                val xrayFtpClient = FTPClient()
                val scalesFtpClient = FTPClient()


                try {
                    licensePlateFtpClient.connect(it.licensePlateHostname)
                    licensePlateFtpClient.login(it.licensePlateUser, it.licensePlatePassword)
                    licensePlateFtpClient.enterLocalPassiveMode()
                    licensePlateFtpClient.changeWorkingDirectory(it.licensePlateDirPath);

                    xrayFtpClient.connect(it.xrayHostname)
                    xrayFtpClient.login(it.xrayUser, it.xrayPassword)
                    xrayFtpClient.enterLocalPassiveMode()
                    xrayFtpClient.changeWorkingDirectory(it.xrayDirPath);

                    scalesFtpClient.connect(it.scalesHostname)
                    scalesFtpClient.login(it.scalesUser, it.scalesPassword)
                    scalesFtpClient.enterLocalPassiveMode()
                    scalesFtpClient.changeWorkingDirectory(it.scalesDirPath);


                    if (licensePlateFtpClient.isConnected && xrayFtpClient.isConnected && scalesFtpClient.isConnected) {
                        for (ftpFile in xrayFtpClient.listFiles()) {
                            // Check if FTPFile is a regular file
                            if (ftpFile.type == FTPFile.FILE_TYPE && ftpFile.name.endsWith(".xml")) {

                                val inputStream = xrayFtpClient.retrieveFileStream(ftpFile.name)
                                val xmlInputFactory = XMLInputFactory.newInstance()
                                val xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream)

                                while (xmlEventReader.hasNext()) {
                                    val event = xmlEventReader.nextEvent()

                                    if (event.eventType == XMLStreamConstants.START_ELEMENT) {
                                        val elementName = event.asStartElement().name.localPart
                                        if (elementName == "your_element_name") {
                                            val text = xmlEventReader.nextEvent().asCharacters().data
                                            println("Element Value: $text")
                                        }
                                    }
                                }

                                xmlEventReader.close()
                                inputStream.close()
                            }
                        }
                    }

                    // Close the FTP connection
                    xrayFtpClient.logout()
                    xrayFtpClient.disconnect()
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    try {
                        xrayFtpClient.disconnect()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

                scanCRUDRepository.save(
                    ScanEntity(
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
                )
            }
        }
    }
}