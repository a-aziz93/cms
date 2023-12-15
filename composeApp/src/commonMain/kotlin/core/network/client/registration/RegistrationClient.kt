package core.network.client.registration

import io.ktor.client.HttpClient


class RegistrationClient(
    private val httpClient: HttpClient
) {

//    suspend fun getCameras(
//        offset: Long,
//        limit:Long,
//        ):  Flow<Resource<CameraResponse?>>{
//        return handleResponse {
//            httpClient.get(NetworkConstants.CameraEndPoint.ROOT) {
//                url {
//                    parameters.append("offset", offset.toString())
//                    parameters.append("limit", limit.toString())
//                }
//                contentType(ContentType.Application.Json)
//            }
//        }
//    }

}