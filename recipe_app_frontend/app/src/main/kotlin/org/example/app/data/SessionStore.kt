package org.example.app.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.app.util.Keys

private val Context.dataStore by preferencesDataStore(name = "session")

/**
 * PUBLIC_INTERFACE
 * Stores auth session email.
 */
class SessionStore(private val context: Context) {
    companion object {
        private val KEY_EMAIL = stringPreferencesKey(Keys.KEY_EMAIL)
    }

    val emailFlow: Flow<String?> = context.dataStore.data.map { prefs: Preferences ->
        prefs[KEY_EMAIL]
    }

    suspend fun setEmail(email: String?) {
        context.dataStore.edit { prefs ->
            if (email == null) prefs.remove(KEY_EMAIL) else prefs[KEY_EMAIL] = email
        }
    }
}
