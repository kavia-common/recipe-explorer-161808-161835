package org.example.app

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

/**
 * PUBLIC_INTERFACE
 * Application class for global initialization.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Picasso with a downloader and logging enabled
        val picasso = Picasso.Builder(this)
            .downloader(OkHttp3Downloader(this, Long.MAX_VALUE))
            .loggingEnabled(true)
            .build()
        Picasso.setSingletonInstance(picasso)
    }
}
