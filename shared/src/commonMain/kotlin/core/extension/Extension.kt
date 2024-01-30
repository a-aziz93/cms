package core.extension

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.lang.IllegalArgumentException
import javax.xml.stream.XMLInputFactory
import kotlin.reflect.KType
import kotlin.time.Duration

suspend fun Duration.repeat(task: suspend () -> Unit): Job {
    val duraion = this
    withContext(Dispatchers.Default) {
        while (true) {
            task()
            delay(duraion.inWholeMilliseconds)
        }
    }
}

suspend fun InputStream.xmlProperties(vararg properties: Pair<String, KType>): Map<String, Any?> {

    val xmlInputFactory = XMLInputFactory.newInstance()

    return properties.associate {
        val reader = xmlInputFactory.createXMLEventReader(this)

        var i = 0
        val propertySplit = it.first.split(".")

        var value: Any? = null

        while (reader.hasNext()) {
            var nextEvent = reader.nextEvent()

            if (nextEvent.isStartElement) {
                val startElement = nextEvent.asStartElement()

                if (startElement.name.localPart == propertySplit[i++] && i >= propertySplit.size) {
                    nextEvent = reader.nextEvent()
                    value = nextEvent.asCharacters().data.to(it.second)
                }
            }

            if (nextEvent.isEndElement) {
                val endElement = nextEvent.asEndElement()
                if (endElement.name.localPart == propertySplit[--i] && i < 1) {
                    break
                }
            }
        }

        it.first to value
    }
}

fun String?.to(type: KType): Any? =
    if (this == null) {
        null
    } else when (type) {
        Boolean::class -> this.toBoolean()
        Char::class -> this[0]
        Byte::class -> this.toByte()
        Short::class -> this.toShort()
        Int::class -> this.toInt()
        Long::class -> this.toLong()
        Float::class -> this.toFloat()
        Double::class -> this.toDouble()
        String::class -> this
        else -> IllegalArgumentException("Can't convert")
    }