package core.network.client.signup

import io.ktor.client.*
import org.koin.core.annotation.Single

@Single
class SignUpClient(
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