plugins {
    alias(libs.plugins.libroom.android.library)
    alias(libs.plugins.libroom.android.lib.compose)
}

android {
    namespace = "com.waseem.mvi"
}

dependencies {
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}