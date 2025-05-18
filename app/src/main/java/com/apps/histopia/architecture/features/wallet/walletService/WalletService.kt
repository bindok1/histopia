package com.apps.histopia.architecture.features.wallet.walletService



import com.apps.histopia.utils.storage.StorageUtils
import com.reown.android.Core
import com.reown.appkit.client.AppKit
import com.reown.appkit.client.Modal
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID


class WalletService @Inject constructor(private val storageUtils: StorageUtils) {
    private val _walletState = MutableStateFlow<WalletState>(WalletState.Initial)
    val walletState: StateFlow<WalletState> = _walletState.asStateFlow()

    suspend fun connectWallet() {
        try {
            _walletState.value = WalletState.Loading


            val savedPairing = storageUtils.getFromStorage()

            val pairing = savedPairing
                ?: Core.Model.Pairing(
                    topic = UUID.randomUUID().toString(),
                    expiry = System.currentTimeMillis() + (30 * 60 * 1000),
                    peerAppMetaData = null,
                    relayProtocol = "irn",
                    relayData = null,
                    uri = "wc:${UUID.randomUUID()}@2",
                    isActive = true,
                    registeredMethods = listOf(
                        "bnb_sign",
                        "bnb_transfer",
                        "eth_sendTransaction",
                        "personal_sign",
                        "eth_signTypedData",
                        "eth_signTransaction"
                    ).joinToString(",")
                )


            AppKit.connect(
                Modal.Params.Connect(pairing = pairing as Core.Model.Pairing),
                onSuccess = { result ->
                    _walletState.value = WalletState.Success(result)
                },
                onError = { error ->
                    _walletState.value = WalletState.Error(
                        message = error.throwable.message ?: "Failed to connect wallet"
                    )
                }
            )

        } catch (e: Exception) {
            _walletState.value = WalletState.Error(
                message = e.message ?: "Failed to connect wallet"
            )
            throw e
        }
    }


    fun handleSessionRequest(request: Core.Model.Message.SessionRequest) {
        _walletState.value = WalletState.Success(request)
    }

    fun handlePairing(pairing: Core.Model.Pairing) {
        _walletState.value = WalletState.Success(pairing)
    }

    fun handleSessionProposal(sessionProposal: Core.Model.Message.SessionProposal) {
        _walletState.value = WalletState.Success(sessionProposal)
    }

    fun handleAuthentication(authenticate: Core.Model.Message.SessionAuthenticate) {
        // Implement authentication logic
        _walletState.value = WalletState.Success(authenticate)
    }

    fun handleSimpleMessage(message: Core.Model.Message.Simple) {
        _walletState.value = WalletState.Success(message)
    }

    fun handleNotification(notify: Core.Model.Message.Notify) {
        _walletState.value = WalletState.Success(notify)
    }

    fun disconnect() {
        // Implement disconnect logic
        _walletState.value = WalletState.Initial
    }
}