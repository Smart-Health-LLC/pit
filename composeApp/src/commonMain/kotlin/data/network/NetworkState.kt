package data.network

sealed class NetworkState {
    data object Good : NetworkState()
    data object Failed : NetworkState()
}
