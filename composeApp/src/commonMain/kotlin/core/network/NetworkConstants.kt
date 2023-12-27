package core.network

object NetworkConstants {
    const val BASE_URL = "http://localhost:8080/"

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