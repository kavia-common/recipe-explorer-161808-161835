package org.example.app.core

/**
 * PUBLIC_INTERFACE
 * App-wide constants for delays, flags, etc.
 */
object AppConstants {
    /** PUBLIC_INTERFACE Debounce delay for search input in milliseconds. */
    const val SEARCH_DEBOUNCE_MS: Long = 250

    /** PUBLIC_INTERFACE Simulated IO delay ranges for repository. */
    const val REPO_DELAY_MS: Long = 300
}
