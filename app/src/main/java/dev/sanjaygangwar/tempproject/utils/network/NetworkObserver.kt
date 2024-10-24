package dev.sanjaygangwar.tempproject.utils.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkObserver(private val connectivityManager: ConnectivityManager) : ConnectivityManager.NetworkCallback() {

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean>
        get() = _isConnected

    init {
        _isConnected.value = false
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        _isConnected.postValue(true)
        Log.d("NetworkObserver", "Network is available")
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        _isConnected.postValue(false)
        Log.d("NetworkObserver", "Network is lost")
    }

    override fun onCapabilitiesChanged(
        network: Network,
        networkCapabilities: NetworkCapabilities
    ) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        // You can perform additional checks here, such as checking for internet connectivity types.
    }
}
