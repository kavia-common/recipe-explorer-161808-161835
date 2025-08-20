package org.example.app.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * PUBLIC_INTERFACE
 * Provides coroutine dispatchers to allow testing and replacement if needed.
 */
object DispatchersProvider {
    val io: CoroutineDispatcher = Dispatchers.IO
    val main: CoroutineDispatcher = Dispatchers.Main
    val default: CoroutineDispatcher = Dispatchers.Default
}
