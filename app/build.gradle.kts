plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.android.room)
    alias(libs.plugins.jetbrains.kotlin.kapt)
}

android {
    namespace = "com.app.basics.daggerhilt"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.basics.daggerhilt"
        minSdk = 27
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.android.core)
    implementation(libs.android.appcompat)
    implementation(libs.android.material)
    implementation(libs.android.constraint.layout)
    // Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gson)
    // Dagger2
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    // Room
    implementation(libs.room)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    // DataStore
    implementation(libs.pref.datastore)

    testImplementation(libs.test.junit)
    androidTestImplementation(libs.andorid.test.junit)
    androidTestImplementation(libs.android.test.espresso)
}