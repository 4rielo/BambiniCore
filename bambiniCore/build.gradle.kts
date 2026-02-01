plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

group = "com.ascarafia"
version = "0.1.0"

kotlin {

    jvmToolchain(17)
    jvm()

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
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        artifactId = "bambinicore"
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ascarafia/BambiniCore")

            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

