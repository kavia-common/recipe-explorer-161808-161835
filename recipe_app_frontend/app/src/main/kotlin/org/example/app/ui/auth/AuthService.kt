package org.example.app.ui.auth

/**
 * PUBLIC_INTERFACE
 * Simple mock auth service validating non-empty email/password.
 */
object AuthService {
    /**
     * PUBLIC_INTERFACE
     * Returns true if credentials are accepted (non-empty).
     */
    fun signIn(email: String, password: String): Boolean {
        return email.isNotBlank() && password.length >= 4
    }
}
