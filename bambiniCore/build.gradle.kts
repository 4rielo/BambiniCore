plugins {
    kotlin("multiplatform")
    //id("com.android.library")
    id("maven-publish")
}

group = "com.ascarafia"
version = "0.1.0"

kotlin {

    jvmToolchain(17)

    //androidTarget()
    //jvm("desktop")

    //iosX64()
    //iosArm64()
    //iosSimulatorArm64()

    sourceSets {
        //val desktopMain by getting

        commonMain {
            dependencies {
                // core puro
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        //androidMain { }
        //iosMain { }
        //desktopMain { }
    }
}

/*
android {
    namespace = "com.ascarafia.bambinicore"
    defaultConfig {
        compileSdk = 34
        minSdk = 30
    }
}

 */