import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

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
    // Koin
    implementation(libs.koin.logger.slf4j) // Koin Logger
    implementation(libs.koin.annotations) // Koin Annotations for KSP
    ksp(libs.koin.ksp.compiler) // Koin KSP Compiler for KSP
}
