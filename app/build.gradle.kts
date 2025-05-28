plugins {
    alias(libs.plugins.libroom.android.application)
    alias(libs.plugins.libroom.android.app.compose)
    alias(libs.plugins.libroom.hilt)
}

android {
    namespace = "com.waseem.libroom"

    defaultConfig {
        applicationId = "com.waseem.libroom"
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
}

dependencies {
    implementation(projects.libroom.core.ui)
    implementation(projects.libroom.feature.auth.ui)
    implementation(projects.libroom.feature.auth.data) //Adding this to make hilt work with transitive dependencies

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}