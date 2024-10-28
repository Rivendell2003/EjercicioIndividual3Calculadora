plugins {
    id("com.android.application")
    kotlin("android") version "1.8.0"
}

android {
    namespace = "com.example.ejercicioindividual3calculadora"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.calculadora"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.kotlin.stdlib.v1720)
    implementation(libs.material.v190)

    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.testng)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
