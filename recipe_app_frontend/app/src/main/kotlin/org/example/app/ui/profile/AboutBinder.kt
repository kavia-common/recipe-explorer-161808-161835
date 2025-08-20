package org.example.app.ui.profile

import android.view.View
import android.widget.TextView
import org.example.app.R

/**
 * PUBLIC_INTERFACE
 * Binds About include with app name and version.
 */
object AboutBinder {
    /**
     * PUBLIC_INTERFACE
     * Bind labels within the provided include view.
     */
    fun bind(includeView: View?) {
        if (includeView == null) return
        val name = includeView.findViewById<TextView>(R.id.tvAppName)
        val version = includeView.findViewById<TextView>(R.id.tvVersion)
        name?.text = includeView.context.getString(R.string.app_name)
        // Static version label; could be wired to BuildConfig.VERSION_NAME
        version?.text = "v1.0.0"
    }
}
