package com.apps.histopia.architecture.features.page.Home

import CustomDisconnectButton
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.apps.histopia.architecture.features.wallet.viewModel.WalletViewModel
import com.apps.histopia.architecture.features.wallet.walletService.WalletState
import com.reown.android.internal.common.scope
import com.reown.appkit.client.AppKit
import com.reown.appkit.ui.components.button.ConnectButton
import com.reown.appkit.ui.components.button.ConnectButtonSize
import com.reown.appkit.ui.components.button.rememberAppKitState
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun HomeScreen(
    walletViewModel: WalletViewModel = hiltViewModel(),
    navController: NavController
) {
    val appKitState = rememberAppKitState(navController = navController)
    val isLoading = appKitState.isOpen.collectAsState(initial = false)
    val isConnected = appKitState.isConnected.collectAsState(initial = false)
    val walletAddress = remember(isConnected.value) {
        if (isConnected.value) {
            AppKit.getAccount()?.address
        } else null
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Home Screen")


            if (isConnected.value) {
                val scope = rememberCoroutineScope()
                val context = LocalContext.current
                CustomDisconnectButton(
                    onClick = {
                        AppKit.disconnect(
                            onSuccess = {
                                scope.launch {
                                    Toast.makeText(
                                        context,
                                        "Succcesfully disconnected wallet",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            onError = { throwable ->
                                scope.launch {
                                    Toast.makeText(
                                        context,
                                        "Error disconnecting: ${throwable.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                    },
                    modifier = Modifier.animateContentSize()
                )
            } else {
                ConnectButton(
                    state = appKitState,
                    buttonSize = ConnectButtonSize.NORMAL
                )
            }

            // Status indicator
            when {
                isLoading.value -> {
                    Text(
                        "Connecting Wallet...",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                isConnected.value -> {
                    Text(
                        "Wallet connected!",
                        color = MaterialTheme.colorScheme.primary
                    )
                    walletAddress?.let { address ->
                        val shortAddress = address.toString().let {
                            "${it.substring(0, 6)}...${it.substring(it.length - 8)}"
                        }
                        Text(
                            text = "Address: $shortAddress",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                else -> {
                    Text(
                        "Wallet not connected!",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}


