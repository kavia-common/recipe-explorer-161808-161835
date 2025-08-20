package org.example.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.example.app.R
import org.example.app.data.SessionStore
import org.example.app.ui.auth.LoginActivity
import org.example.app.ui.favorites.FavoritesFragment
import org.example.app.ui.home.HomeFragment
import org.example.app.ui.profile.ProfileFragment
import org.example.app.ui.search.SearchFragment

/**
 * PUBLIC_INTERFACE
 * MainActivity hosts bottom navigation (Home, Search, Favorites, Profile).
 *
 * Parameters: None
 * Returns: Displays fragments and manages navigation.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var nav: BottomNavigationView
    private lateinit var session: SessionStore

    private var homeFragment: Fragment? = null
    private var searchFragment: Fragment? = null
    private var favoritesFragment: Fragment? = null
    private var profileFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

        session = SessionStore(this)

        lifecycleScope.launch {
            val email = session.emailFlow.first()
            if (email.isNullOrBlank()) {
                val i = Intent(this@MainActivity, LoginActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
                return@launch
                override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("selected_nav_item", nav.selectedItemId)
    }
}
        }

        nav = findViewById(R.id.bottomNav)
        nav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    title = getString(R.string.title_home)
                    if (homeFragment == null) homeFragment = HomeFragment()
                    switchFragment(homeFragment!!)
                    true
                }
                R.id.nav_search -> {
                    title = getString(R.string.title_search)
                    if (searchFragment == null) searchFragment = SearchFragment()
                    switchFragment(searchFragment!!)
                    true
                }
                R.id.nav_favorites -> {
                    title = getString(R.string.title_favorites)
                    if (favoritesFragment == null) favoritesFragment = FavoritesFragment()
                    switchFragment(favoritesFragment!!)
                    true
                }
                R.id.nav_profile -> {
                    title = getString(R.string.title_profile)
                    if (profileFragment == null) profileFragment = ProfileFragment()
                    switchFragment(profileFragment!!)
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            title = getString(R.string.title_home)
            nav.selectedItemId = R.id.nav_home
        } else {
            // Restore previously selected item
            val selected = savedInstanceState.getInt("selected_nav_item", R.id.nav_home)
            nav.selectedItemId = selected
        }
    }

    private fun switchFragment(fragment: Fragment) {
        val tx = supportFragmentManager.beginTransaction()
        tx.replace(R.id.fragmentContainer, fragment)
        tx.setReorderingAllowed(true)
        tx.commit()
    }
}
