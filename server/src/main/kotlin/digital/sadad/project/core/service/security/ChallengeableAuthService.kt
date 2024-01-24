package digital.sadad.project.core.service.security

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

interface ChallengeableAuthService {
    suspend fun challenge(call: ApplicationCall, vararg args: Any) =
        call.respond(HttpStatusCode.Unauthorized, "Credentials are not valid")
}