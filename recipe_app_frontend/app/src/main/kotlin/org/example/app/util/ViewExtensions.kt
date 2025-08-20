package org.example.app.util

import android.view.View

/**
 * PUBLIC_INTERFACE
 * Common view extensions for visibility handling.
 */
fun View.setVisible() {
    this.visibility = View.VISIBLE
}

/**
 * PUBLIC_INTERFACE
 * Set view visibility to GONE.
 */
fun View.setGone() {
    this.visibility = View.GONE
}
