plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
    alias(libs.plugins.allopen)
    alias(libs.plugins.noarg)
}

kotlin {
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        browser()
//    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()

    jvm()

    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            // Added
            implementation(libs.kotlin.reflect)
            implementation(libs.coroutines.core)
            implementation(libs.kotlinx.io.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.bundles.koin)
            implementation(libs.kotlin.result)
            implementation(libs.kotlin.noarg)
            implementation(libs.bundles.kmputils)
            implementation(libs.bundles.kotlinx.serialization)
            implementation(libs.bundles.ktor.common)
            implementation(libs.bundles.slf4j)
            implementation(libs.kconmapper.annotations)
        }
    }
}

android {
    namespace = "digital.sadad.project.shared"
    compileSdk = libs.versions.android.compile.sdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.min.sdk.get().toInt()
    }
}

dependencies {
    // Koin KSP Compiler for KSP
    ksp(libs.koin.ksp.compiler)

    // DTO Mapper
    ksp(libs.kconmapper.ksp)
}

ksp {
    arg("kconmapper.suppressMappingMismatchWarnings", "true")
}

allOpen {
    annotation("core.annotation.clazz.Open")
}

noArg {
    annotation("core.annotation.clazz.NoArg")
}
