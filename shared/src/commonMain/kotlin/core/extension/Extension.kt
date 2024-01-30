package core.extension

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.lang.IllegalArgumentException
import javax.xml.stream.XMLInputFactory
import kotlin.reflect.KClass
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

fun InputStream.xmlProperties(properties: Map<String, KClass<*>>): Map<String, Any?> {

    val xmlInputFactory = XMLInputFactory.newInstance()

    return properties.entries.associate {
        val reader = xmlInputFactory.createXMLEventReader(this)

        var i = 0
        val propertySplit = it.key.split(".")

        var value: Any? = null

        while (reader.hasNext()) {
            var nextEvent = reader.nextEvent()

            if (nextEvent.isStartElement) {
                val startElement = nextEvent.asStartElement()

                if (startElement.name.localPart == propertySplit[i]) {
                    if (++i >= propertySplit.size) {
                        nextEvent = reader.nextEvent()
                        value = nextEvent.asCharacters().data.convert(it.value)
                        break
                    }
                }
            }
        }

        it.key to value
    }
}

fun String?.convert(type: KClass<*>): Any? =
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