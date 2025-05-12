package com.apps.histopia.architecture.features.wallet.walletService

sealed class WalletState {
    data object Loading : WalletState()
    data class Success(val result: Any) : WalletState()


    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : WalletState()

    data object Initial : WalletState()
}