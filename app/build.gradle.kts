plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
}

android {
    namespace = "com.app.basics.daggerhilt"
    compileSdk = libs.versions.compileSdk.get().toInt()

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
    implementation(project(":storage"))

    implementation(libs.android.ktx)
    implementation(libs.android.appcompat)
    implementation(libs.material)
    implementation(libs.constraint.layout)
    // Dagger2
    // implementation("com.google.dagger:dagger:2.48")
    // kapt("com.google.dagger:dagger-compiler:2.48")
    // Hilt
    implementation(libs.hilt.andriod)
    kapt(libs.hilt.compiler)
    // For ViewModel and Lifecycles
    implementation (libs.activity.ktx)

    testImplementation(libs.test.junit)
    androidTestImplementation(libs.android.test.junit)
    androidTestImplementation(libs.android.test.espresso)
}