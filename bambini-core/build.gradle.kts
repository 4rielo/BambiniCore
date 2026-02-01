plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

group = "com.ascarafia"
version = "0.1.9"

kotlin {

    jvmToolchain(17)
    jvm()

    iosArm64()
    iosSimulatorArm64()

    sourceSets {

        commonMain {
            dependencies {
                // core puro
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

    /*
    //androidTarget()
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")
    */

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

