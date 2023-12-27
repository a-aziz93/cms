import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.apollo3)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.dokka)
}

kotlin {
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        moduleName = "composeApp"
//        browser {
//            commonWebpackConfig {
//                outputFileName = "composeApp.js"
//            }
//        }
//        binaries.executable()
//    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm("desktop")

//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach { iosTarget ->
//        iosTarget.binaries.framework {
//            baseName = "ComposeApp"
//            isStatic = true
//            export(libs.decompose)
//            export(libs.lifecycle)
//        }
//    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            // Added
            implementation(libs.koin.android)
            implementation(libs.ktor.client.android)
            implementation(libs.sql.delight.android.driver)
            implementation(libs.android.play.core)
            implementation(libs.coroutines.android)
        }
        val commonMain by getting {
            kotlin.srcDirs("build/generated/ksp/commonMain/kotlin")
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(projects.shared)
                // Added
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(libs.kermit)
                implementation(libs.lyricist)
//            implementation(libs.material3.adaptive)
//            implementation(libs.material3.adaptive.navigation.suite)
//            implementation(libs.material3.window.size)
                implementation(libs.material3.window.size.multiplatform)
                implementation(libs.bundles.compose.icons)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.bundles.multiplatform.settings)
                implementation(libs.bundles.koin)
                implementation(libs.koin.compose)
                implementation(libs.bundles.ktor.client)
                implementation(libs.bundles.sql.delight)
                implementation(libs.bundles.mvi.kotlin)
                implementation(libs.bundles.decompose)
                implementation(libs.lifecycle)
                implementation(libs.kamel.image)
                implementation(libs.kotlin.result)
                implementation(libs.kotlin.reflect)
            }
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.ktor.client.java)
            implementation(libs.sql.delight.sqlite.driver)
            implementation(libs.coroutines.swing)
        }

//            iosMain.dependencies {
//                implementation(libs.ktor.client.darwin)
//                implementation(libs.sql.delight.native.driver)
//            }

//            jsMain.dependencies{
//                implementation(libs.ktor.client.js)
//                implementation(libs.sql.delight.js.driver)
//                implementation(npm("@cashapp/sqldelight-sqljs-worker", "2.0.1"))
//                implementation(npm("sql.js", "1.8.0"))
//                implementation(devNpm("copy-webpack-plugin", "9.1.0"))
//            }
    }
}

android {
    namespace = "digital.sadad.project"
    compileSdk = libs.versions.android.compile.sdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "digital.sadad.project"
        minSdk = libs.versions.android.min.sdk.get().toInt()
        targetSdk = libs.versions.android.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "digital.sadad.project"
            packageVersion = "1.0.0"
        }
    }
}

compose.experimental {
    web.application {}
}

dependencies {
    add("kspCommonMainMetadata", libs.lyricist.processor)
    // For import org.koin.ksp.generated.*
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
}

// workaround for KSP only in Common Main.
// https://github.com/google/ksp/issues/567
tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}

ksp {
    // Localization
    arg("lyricist.internalVisibility", "true")
    arg("lyricist.generateStringsProperty", "true")
    // Compile Safety - check your Koin config at compile time (since 1.3.0)
    arg("KOIN_CONFIG_CHECK", "true")


}