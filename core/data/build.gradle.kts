plugins {
    alias(libs.plugins.libroom.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.libroom.core.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.domain)
}