package org.example.app.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.app.util.Keys

private val Context.favDataStore by preferencesDataStore(name = "favorites")

/**
 * PUBLIC_INTERFACE
 * Manages favorites with DataStore.
 */
class FavoritesStore(private val context: Context) {

    companion object {
        private val KEY_FAVORITES = stringSetPreferencesKey(Keys.KEY_FAVORITES_SET)
    }

    val favoritesFlow: Flow<Set<String>> = context.favDataStore.data.map { prefs: Preferences ->
        prefs[KEY_FAVORITES] ?: emptySet()
    }

    suspend fun toggleFavorite(id: String) {
        context.favDataStore.edit { prefs ->
            val current = prefs[KEY_FAVORITES]?.toMutableSet() ?: mutableSetOf()
            if (current.contains(id)) current.remove(id) else current.add(id)
            prefs[KEY_FAVORITES] = current
        }
    }
}
