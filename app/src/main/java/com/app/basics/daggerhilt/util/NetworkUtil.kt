package com.app.basics.daggerhilt.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class NetworkUtil @Inject constructor(private val context: Application) {

    fun isConnectedToNetwork(): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork

        val networkCapability = connectivityManager.getNetworkCapabilities(network)

        networkCapability?.let { capability ->
            return capability.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && capability.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_VALIDATED
            )
        }
        return false
    }

}