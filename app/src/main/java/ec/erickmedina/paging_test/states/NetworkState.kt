package ec.erickmedina.paging_test.states

sealed class NetworkState {
    object Loading : NetworkState()
    object Success: NetworkState()
    class Error(val error: Any) : NetworkState()
}