package core.network.helper

import core.network.Resource
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.flow

//suspend inline fun <reified T> handleResponse(
//    crossinline response: suspend () -> HttpResponse
//) : flow {
//
//    emit(Resource.loading())
//    
//    val result = try {
//        response()
//    } catch(e: IOException) {
//        return Resource.error(error = NetworkError(code = NetworkErrorCode.SERVICE_UNAVAILABLE))
//    }
//
//    when(result.status.value) {
//        in 200..299 -> Unit
//        in 400..499 -> emit(Resource.error(error = NetworkError(code = NetworkErrorCode.CLIENT_ERROR)))
//        500 -> emit(Resource.error(error = NetworkError(code = NetworkErrorCode.SERVER_ERROR)))
//        else ->emit(Resource.error(error = NetworkError(code = NetworkErrorCode.UNKNOWN_ERROR)))
//    }
//
//    return@withContext try {
//        result.body()
//    } catch(e: Exception) {
//        emit(Resource.error(error = NetworkError(code = NetworkErrorCode.SERVER_ERROR)))
//    }
//}