package com.cwramirezg.noticia.util

import android.content.Context
import android.net.ConnectivityManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ExtensionStatic : KoinComponent {

    fun isOnline(): Boolean {
        val context: Context by inject()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetwork = cm?.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }

}