plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization") version "1.8.0"
}

android {
    namespace = "com.example.chatappclient"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.chatappclient"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation ("androidx.compose.material3:material3:1.1.0-alpha09")
    implementation ("androidx.compose.ui:ui:1.4.3")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.4.3")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    val compose_version = "1.8.0"
    val koin_version = "3.3.0"
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Coroutine lifecycle scope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    // Koin for Android
    implementation("io.insert-koin:koin-android:$koin_version")
    // Jetpack Compose
    implementation("io.insert-koin:koin-androidx-compose:$koin_version")

    // Ktor for Android
    implementation("io.ktor:ktor-client-core:2.1.2")
    implementation("io.ktor:ktor-client-cio:2.1.2")
    implementation("io.ktor:ktor-client-websockets:2.1.2")
    implementation("io.ktor:ktor-client-serialization:2.1.2")
    implementation("io.ktor:ktor-client-content-negotiation:2.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0")
    implementation("io.ktor:ktor-client-logging-jvm:2.1.2")

    // Gson
    implementation("com.google.code.gson:gson:2.9.1")

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.2")
    testImplementation("junit:junit:4.13.2")
    implementation("androidx.compose.material3:material3-android:1.2.1")

    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.androidx.espresso.core.v340)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")


}