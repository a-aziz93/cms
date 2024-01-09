package digital.sadad.project.core.extension.sse

import digital.sadad.project.core.model.FlowEvent
import digital.sadad.project.core.extension.sse.model.SseEvent
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

/**
 * Method that responds an [ApplicationCall] by reading all the [SseEvent]s from the specified [events] [ReceiveChannel]
 * and serializing them in a way that is compatible with the Server-Sent Events specification.
 *
 * You can read more about it here: https://www.html5rocks.com/en/tutorials/eventsource/basics/
 */
suspend fun ApplicationCall.respondSse(flowEvent: FlowEvent<SseEvent>) {
    response.cacheControl(CacheControl.NoCache(null))
    respondTextWriter(contentType = ContentType.Text.EventStream) {
        flowEvent.events.onEach { event ->
            if (event.id != null) {
                write("id: ${event.id}\n")
            }
            if (event.event != null) {
                write("event: ${event.event}\n")
            }
            for (dataLine in event.data.lines()) {
                write("data: $dataLine\n")
            }
            write("\n")
            flush()
        }
            .flowOn(Dispatchers.IO)
    }
}