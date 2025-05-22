plugins {
    alias(libs.plugins.libroom.android.feature)
    alias(libs.plugins.libroom.android.lib.compose)
}

android {
    namespace = "com.libroom.auth.ui"
}

dependencies {
    implementation(projects.feature.auth.domain)
}