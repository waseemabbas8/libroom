plugins {
    alias(libs.plugins.libroom.android.library)
}

android {
    namespace = "com.libroom.core.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.domain)
}