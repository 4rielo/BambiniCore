plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
}

group = providers.gradleProperty("GROUP").get()
version = providers.gradleProperty("VERSION_NAME").get()

kotlin {
    androidLibrary {
        namespace = "com.ascarafia.bambini_data_network"
        compileSdk = 36
        minSdk = 30

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    jvmToolchain(17)
    jvm("desktop")

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(project(":bambini-core"))
            implementation(libs.kotlin.stdlib)
            // Add KMP dependencies here
            implementation(libs.kotlinx.serialization.json)

            api(libs.kotlinx.coroutines.core)

            implementation(libs.ktor.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.logging)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        desktopMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        androidMain.dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
            implementation(libs.ktor.client.okhttp)
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.runner)
                implementation(libs.core)
                implementation(libs.junit)
            }
        }

        iosMain.dependencies {
                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
            implementation(libs.ktor.client.darwin)
        }
    }

}