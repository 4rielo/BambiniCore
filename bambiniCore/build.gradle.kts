plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

group = "com.ascarafia"
version = "0.1.5"

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
        artifactId = when (name) {
            "kotlinMultiplatform" -> "bambinicore"
            "jvm" -> "bambinicore-jvm"
            else -> artifactId
        }
        if (name == "jvm") {
            suppressPomMetadataWarningsFor("jvm")
        }
    }

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

