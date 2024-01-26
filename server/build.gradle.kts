plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.dokka)
    alias(libs.plugins.allopen)
    alias(libs.plugins.noarg)
}

group = "digital.sadad.project"
version = "1.0.0"
application {
    mainClass.set("digital.sadad.project.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

val osName = System.getProperty("os.name").lowercase()
val tcnativeClassifier = when {
    osName.contains("win") -> "windows-x86_64"
    osName.contains("linux") -> "linux-x86_64"
    osName.contains("mac") -> "osx-x86_64"
    else -> null
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.bundles.ktor.common)
    implementation(libs.bundles.ktor.server)
    implementation("io.ktor:ktor-server-caching-headers-jvm:2.3.6")
    implementation("io.ktor:ktor-server-default-headers-jvm:2.3.6")
    implementation("io.ktor:ktor-server-conditional-headers-jvm:2.3.6")
    implementation("io.ktor:ktor-server-forwarded-header-jvm:2.3.6")
    implementation("io.ktor:ktor-server-hsts-jvm:2.3.6")
    implementation("io.ktor:ktor-server-http-redirect-jvm:2.3.6")
    implementation("io.ktor:ktor-server-partial-content-jvm:2.3.6")
    implementation("io.ktor:ktor-server-call-logging-jvm:2.3.6")
    implementation("io.ktor:ktor-server-call-id-jvm:2.3.6")
    implementation("io.ktor:ktor-server-core-jvm:2.3.6")
    implementation("io.ktor:ktor-server-metrics-micrometer-jvm:2.3.6")
    testImplementation(libs.kotlin.test.junit)
    // Added
    implementation(libs.ktor.swagger.ui)
    implementation(libs.kotlin.logging.jvm)
    implementation(libs.koin.ktor)
    implementation(libs.cache4k)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.server.database)
    implementation(libs.kotlin.result)
    implementation(libs.bcrypt)
    implementation(libs.reflections)
    implementation(libs.hoplite.core)
    implementation(libs.bundles.kmputils)
    // To enable HTTP/2 in Netty, use OpenSSL bindings (tcnative netty port).
    // The below shows how to add a native implementation (statically linked BoringSSL library, a fork of OpenSSL)
    if (tcnativeClassifier != null) {
        implementation("io.netty:netty-tcnative-boringssl-static:${libs.versions.tcnative.get()}:$tcnativeClassifier")
    } else {
        implementation("io.netty:netty-tcnative-boringssl-static:${libs.versions.tcnative.get()}")
    }
    // For import org.koin.ksp.generated.*
//    add("kspServerMainMetadata", libs.koin.ksp.compiler)
    implementation(libs.kgraphql)
    implementation(libs.kgraphql.ktor)
    implementation(libs.keycloak.admin.client)
    implementation(libs.sql.delight.sqlite.driver)
    implementation(libs.bundles.metrics)
}

ksp {
    // Compile Safety - check your Koin config at compile time (since 1.3.0)
    arg("KOIN_CONFIG_CHECK", "true")
}


sqldelight {
    databases {
        create("SharedDatabase") {
            srcDirs.setFrom("src/main/sqldelight")
            packageName.set("digital.sadad.project.common")
            generateAsync.set(true)
        }
    }
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