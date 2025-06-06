plugins {
    alias(libs.plugins.libroom.android.library)
    alias(libs.plugins.libroom.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.libroom.auth.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.feature.auth.domain)
    implementation(projects.core.data)
}