import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinSerialization)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.jddev.androidcorearchlite"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jddev.androidcorearchlite"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            //applicationIdSuffix = ".debug"
            versionNameSuffix = "_debug"
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }

        applicationVariants.all {
            outputs.all {
                (this as? BaseVariantOutputImpl)?.outputFileName =
                    "CoreArch_${versionName}_${
                        SimpleDateFormat("yyMMdd", Locale.US).format(
                            Date()
                        )
                    }.apk"
            }
        }
    }

    flavorDimensions += "app_type"
    val dev = "dev"
    val prd = "prd"
    productFlavors {
        create(dev) {
            dimension = "app_type"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            buildConfigField("String", "DEV_MODE", "\"dev\"")
            resValue("string", "app_name", "Core Arch Dev")
        }
        create(prd) {
            dimension = "app_type"
            versionNameSuffix = "-prd"
            buildConfigField("String", "DEV_MODE", "\"prd\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":simpletouch-ui"))
    // ui module
    implementation(libs.androidx.material.icon)

    // navigation
    implementation(libs.androidx.navigation)

    // hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(project(":simpletouch-utils"))
    kapt(libs.hilt.android.compiler)
    // room
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.timber)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}