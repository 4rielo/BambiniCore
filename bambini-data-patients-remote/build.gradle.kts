import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)

    id("maven-publish")

    alias(libs.plugins.serialization)
}

group = providers.gradleProperty("GROUP").get()
version = providers.gradleProperty("VERSION_NAME").get()

kotlin {
    androidLibrary {
        namespace = "com.ascarafia.bambini"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withJava() // enable java compilation support
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
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
            implementation(project(":bambini-data-network"))

            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.ktor.core)
            implementation(libs.kotlinx.serialization.json)
        }

        androidMain.dependencies {

        }

        desktopMain.dependencies {
        }

        iosMain.dependencies {
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/4rielo/BambiniCore")

            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

