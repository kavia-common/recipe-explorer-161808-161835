androidApplication {
    namespace = "org.example.app"

    android {
        // Enable View Binding for traditional Android Views (no Compose)
        buildFeatures {
            viewBinding = true
        }
    }

    dependencies {
        // AndroidX core and UI
        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.appcompat:appcompat:1.7.0")
        implementation("com.google.android.material:material:1.12.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation("androidx.recyclerview:recyclerview:1.3.2")
        implementation("androidx.activity:activity-ktx:1.9.2")
        implementation("androidx.fragment:fragment-ktx:1.8.3")
        implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

        // Lifecycle
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

        // DataStore
        implementation("androidx.datastore:datastore-preferences:1.1.1")

        // Images
        implementation("com.squareup.picasso:picasso:2.8")
        implementation("com.squareup.okhttp3:okhttp:4.12.0")

        // JSON parsing
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    }
}
