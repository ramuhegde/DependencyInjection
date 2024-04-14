plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.android.room)
}

android {
    namespace = "com.app.basics.daggerhilt"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = "com.app.basics.daggerhilt"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
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
    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":network"))

    implementation(libs.android.ktx)
    implementation(libs.android.appcompat)
    implementation(libs.material)
    implementation(libs.constraint.layout)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    // Dagger2
    // implementation("com.google.dagger:dagger:2.48")
    // kapt("com.google.dagger:dagger-compiler:2.48")
    // Hilt
    implementation(libs.hilt.andriod)
    kapt(libs.hilt.compiler)
    // For ViewModel and Lifecycles
    implementation (libs.activity.ktx)
    // Room
    implementation(libs.android.room)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    // Preferences DataStore
    implementation(libs.preference.datastore)

    testImplementation(libs.test.junit)
    androidTestImplementation(libs.android.test.junit)
    androidTestImplementation(libs.android.test.espresso)
}