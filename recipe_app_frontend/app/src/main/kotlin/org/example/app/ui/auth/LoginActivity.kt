package org.example.app.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.example.app.R
import org.example.app.data.SessionStore
import org.example.app.ui.MainActivity

/**
 * PUBLIC_INTERFACE
 * LoginActivity handles user login and redirects to MainActivity.
 *
 * Parameters: None (UI-driven).
 * Returns: Navigates to MainActivity upon success.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var session: SessionStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        session = SessionStore(this)

        lifecycleScope.launch {
            val email = session.emailFlow.first()
            if (!email.isNullOrBlank()) {
                // Already logged in
                goToMain()
            }
        }

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btn = findViewById<Button>(R.id.btnSignIn)

        btn.setOnClickListener {
            val email = etEmail.text?.toString()?.trim().orEmpty()
            val pass = etPassword.text?.toString()?.trim().orEmpty()

            var hasError = false
            if (!org.example.app.util.EmailValidator.isValid(email)) {
                etEmail.error = getString(R.string.invalid_email)
                hasError = true
            } else {
                etEmail.error = null
            }
            if (pass.length < 4) {
                etPassword.error = getString(R.string.invalid_password)
                hasError = true
            } else {
                etPassword.error = null
            }
            if (hasError) return@setOnClickListener

            if (AuthService.signIn(email, pass)) {
                lifecycleScope.launch {
                    session.setEmail(email)
                    goToMain()
                }
            } else {
                Toast.makeText(this, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToMain() {
        val i = Intent(this, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }
}
