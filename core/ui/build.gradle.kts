plugins {
    alias(libs.plugins.libroom.android.library)
    alias(libs.plugins.libroom.android.lib.compose)
}

android {
    namespace = "com.libroom.core.ui"
}

dependencies {
    api(libs.androidx.material3)
}