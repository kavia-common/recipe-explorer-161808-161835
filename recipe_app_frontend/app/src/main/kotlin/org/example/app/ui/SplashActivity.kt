package org.example.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.example.app.R
import org.example.app.data.SessionStore
import org.example.app.ui.auth.LoginActivity

/**
 * PUBLIC_INTERFACE
 * SplashActivity routes user to Login or Main based on session.
 *
 * Parameters: None
 * Return: Navigates to the appropriate Activity then finishes itself.
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var session: SessionStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        session = SessionStore(this)

        lifecycleScope.launch {
            val email = session.emailFlow.first()
            val next = if (email.isNullOrBlank()) {
                Intent(this@SplashActivity, LoginActivity::class.java)
            } else {
                Intent(this@SplashActivity, MainActivity::class.java)
            }
            next.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(next)
            finish()
        }
    }
}
