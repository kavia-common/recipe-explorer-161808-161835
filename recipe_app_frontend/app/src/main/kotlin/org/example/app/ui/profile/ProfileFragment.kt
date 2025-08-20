package org.example.app.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.example.app.R
import org.example.app.data.FavoritesStore
import org.example.app.data.SessionStore
import org.example.app.ui.auth.LoginActivity

/**
 * PUBLIC_INTERFACE
 * Profile screen showing current user and logout, with About and Help options.
 */
class ProfileFragment : Fragment() {

    private lateinit var session: SessionStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = SessionStore(requireContext())
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val tv = root.findViewById<TextView>(R.id.tvGreeting)
        val btn = root.findViewById<Button>(R.id.btnLogout)

        viewLifecycleOwner.lifecycleScope.launch {
            val email = session.emailFlow.first() ?: getString(R.string.guest)
            tv.text = getString(R.string.profile_greeting, email)
        }

        // Bind About include (version info)
        val about = root.findViewById<View>(R.id.includeAbout)
        AboutBinder.bind(about)

        btn.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                session.setEmail(null)
                val i = Intent(requireContext(), LoginActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
            }
        }

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_about, null)
                val dlg = AlertDialog.Builder(requireContext())
                    .setView(dialogView)
                    .setPositiveButton(R.string.ok, null)
                    .create()
                dlg.show()
                val include = dialogView.findViewById<View>(R.id.includeAbout)
                AboutBinder.bind(include)
                return true
            }
            R.id.action_help -> {
                startActivity(Intent(requireContext(), HelpActivity::class.java))
                return true
            }
            R.id.action_clear_favorites -> {
                // QA utility: clear all favorites
                viewLifecycleOwner.lifecycleScope.launch {
                    val favorites = FavoritesStore(requireContext())
                    val current = favorites.favoritesFlow.first()
                    current.forEach { id -> favorites.toggleFavorite(id) }
                    android.widget.Toast.makeText(requireContext(), getString(R.string.removed_from_favorites), android.widget.Toast.LENGTH_SHORT).show()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
