plugins {
    alias(libs.plugins.libroom.android.library)
    alias(libs.plugins.libroom.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.waseem.core.network"
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    api(libs.ktor.client.resources)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
}