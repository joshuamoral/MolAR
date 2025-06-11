plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.molar2"
    compileSdk = 35

    sourceSets {
        getByName("main") {
            assets.srcDirs("src/main/assets")
        }
    }
    defaultConfig {
        applicationId = "com.example.molar2"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }


}

dependencies {



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.androidx.navigation.compose)

    //implementation (libs.material3)

    implementation (libs.androidx.foundation)

    implementation (libs.accompanist.flowlayout)

    implementation (libs.androidx.material3.v121)

    implementation (libs.androidx.material.icons.extended)

    implementation (libs.androidx.activity.activity.compose.v190.x6)
    implementation (libs.androidx.compose.ui.ui9)
    implementation (libs.xandroidx.compose.material.material10)
    implementation (libs.ui.tooling.preview)
    implementation (libs.androidx.material.icons.extended.v167)
    debugImplementation (libs.ui.tooling)

    implementation (libs.androidx.navigation.compose.v277)


    implementation(libs.androidx.navigation.compose)
    implementation(libs.arsceneview)
    implementation(libs.core)
    //implementation(libs.kotlinx.serialization.json)

    implementation (libs.journeyapps.zxing.android.embedded)
    implementation (libs.zxing.core)

    implementation (libs.jetbrains.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    //QR
    implementation (libs.androidx.camera.lifecycle)
    implementation (libs.androidx.camera.view)
    implementation (libs.barcode.scanning)
    implementation (libs.androidx.camera.core)
    implementation (libs.androidx.camera.camera2)







}