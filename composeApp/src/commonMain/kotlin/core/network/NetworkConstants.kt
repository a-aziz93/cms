package core.network

import SERVER_PORT

object NetworkConstants {
    const val BASE_URL = "http://localhost:$SERVER_PORT/"

    object CameraEndPoint {
        const val ROOT = BASE_URL + "camera"
    }
    
    object ScalesEndPoint {
        const val ROOT = BASE_URL + "scales"
    }
    
    object XRayEndPoint {
        const val ROOT = BASE_URL + "xray"
    }
}