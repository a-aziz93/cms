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
    implementation(libs.koin.ktor)
    implementation(libs.cache4k)
}