package com.apps.histopia.architecture.features.wallet.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.histopia.architecture.features.wallet.walletService.WalletService
import com.apps.histopia.architecture.features.wallet.walletService.WalletState
import com.reown.android.Core
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class WalletViewModel @Inject constructor(
    val walletService: WalletService
) : ViewModel() {

    val walletState: StateFlow<WalletState> = walletService.walletState


    fun handleSessionProposal(sessionProposal: Core.Model.Message.SessionProposal) {
        viewModelScope.launch {
            try {
                walletService.handleSessionProposal(sessionProposal)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun connectWallet() {
        viewModelScope.launch {
            try {
                walletService.connectWallet()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun handleSessionRequest(sessionRequest: Core.Model.Message.SessionRequest) {
        viewModelScope.launch {
            try {
                walletService.handleSessionRequest(sessionRequest)
            } catch (e: Exception) {

            }
        }
    }


    fun handleAuthentication(authenticate: Core.Model.Message.SessionAuthenticate) {
        viewModelScope.launch {
            try {
                walletService.handleAuthentication(authenticate)
            } catch (e: Exception) {
                Timber.e(e, "Error authentication wallet")
            }
        }
    }


    fun handlePairing(pairing: Core.Model.Pairing) {
        viewModelScope.launch {
            try {
                walletService.handlePairing(pairing)
            } catch (e: Exception) {
                Timber.e(e, "Error pairing wallet")
            }
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            try {
                walletService.disconnect()
            } catch (e: Exception) {
                Timber.e(e, "Error disconnecting wallet")
            }
        }
    }

    fun handleSimpleMessage(message: Core.Model.Message.Simple) {
        viewModelScope.launch {
            try {
            } catch (e: Exception) {
            }
        }
    }


    fun handleNotification(notify: Core.Model.Message.Notify) {
        viewModelScope.launch {
            try {
            } catch (e: Exception) {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            try {
                walletService.disconnect()
            } catch (e: Exception){
                Timber.e("onCleared: ${e.message}")
            }
        }
    }

}
