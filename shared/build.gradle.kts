import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {

        commonMain.dependencies {
            // put your Multiplatform dependencies here
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.kotlinx.datetime)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.serialization)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.cio)

            implementation(libs.multiplatform.settings)
            //koin
            implementation(libs.koin.core)
        }

        androidMain.dependencies {
            implementation(libs.androidx.compose.ui)
            //ktor
            implementation(libs.ktor.client.android)
            //Data store
            implementation(libs.data.store)
            //Auth0
            implementation(libs.auth0.android)
            implementation(libs.jwtdecode)

            //Koin
            implementation(libs.koin.android)
        }

        iosMain.dependencies {
            //Network
            implementation(libs.ktor.client.ios)
        }
    }
}

android {
    namespace = "org.big3.clyq.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        manifestPlaceholders["auth0Domain"] = "dev-j8fde724yfmga0ao.us.auth0.com"
        manifestPlaceholders["auth0Scheme"] = "clyq"
    }
}
dependencies {
    implementation(libs.androidx.core.ktx)
}

