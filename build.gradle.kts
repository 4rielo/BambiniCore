plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    /*
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
     */
    alias(libs.plugins.kotlinMultiplatform) apply false
}

/* Added because of chatgpt
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
    }
}

 */