package org.example.app.util

import android.util.Patterns

/**
 * PUBLIC_INTERFACE
 * Utility to validate email addresses.
 */
object EmailValidator {
    /**
     * PUBLIC_INTERFACE
     * Returns true if the input is a valid email address format.
     */
    fun isValid(email: String?): Boolean {
        if (email.isNullOrBlank()) return false
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
