plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)
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
    ksp(libs.koin.ksp.compiler) // Koin KSP Compiler for KSP
}
