import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

}
//val apikey = project.findProperty("apiKey1")
android {
    namespace = "com.example.ai_voice_assistant"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ai_voice_assistant"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        android.buildFeatures.buildConfig = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val apiKey: String = project.findProperty("apiKey1") as? String ?: ""
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
    }

    buildFeatures{
        viewBinding = true
        buildConfig = true
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(libs.generativeai)

    implementation("com.google.ai.client.generativeai:generativeai:0.7.0")
}