plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.dokka)
}

group = "digital.sadad.project"
version = "1.0.0"
application {
    mainClass.set("digital.sadad.project.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.bundles.ktor.server)
    testImplementation(libs.kotlin.test.junit)
    // Added
    implementation(libs.kotlin.logging.jvm)
    implementation(libs.koin.ktor)
    implementation(libs.cache4k)
    implementation(libs.bundles.koin)
    implementation(libs.kotysa.r2dbc)
    implementation(libs.r2dbc.pool)
    implementation(libs.r2dbc.h2)
    implementation(libs.oracle.r2dbc)
    implementation(libs.kotlin.result)
    implementation(libs.bcrypt)
    implementation(libs.reflections)
}

// To generate Docker Image with JRE 17
//ktor {
//    docker {
//        localImageName.set("hyperskill-reactive-api-kotlin-ktor")
//        imageTag.set("0.0.1-preview")
//        jreVersion.set(io.ktor.plugin.features.JreVersion.JRE_17)
//        portMappings.set(
//            listOf(
//                io.ktor.plugin.features.DockerPortMapping(
//                    8080,
//                    8080,
//                    io.ktor.plugin.features.DockerPortMappingProtocol.TCP
//                ),
//                io.ktor.plugin.features.DockerPortMapping(
//                    8083,
//                    8083,
//                    io.ktor.plugin.features.DockerPortMappingProtocol.TCP
//                )
//            )
//        )
//    }
//}