package com.apps.histopia.architecture.features.page.Home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apps.histopia.architecture.features.page.Home.widget.WalletConnectButton
import com.apps.histopia.architecture.features.wallet.viewModel.WalletViewModel
import com.apps.histopia.architecture.features.wallet.walletService.WalletState
import timber.log.Timber

@Composable
fun HomeScreen(
    walletViewModel: WalletViewModel = hiltViewModel()
) {
    val walletState =
        walletViewModel.walletState.collectAsState(
            initial = WalletState.Initial)
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Home Screen")
            WalletConnectButton(walletViewModel = walletViewModel)
            // Status indicator
            when (walletState.value) {
                is WalletState.Initial -> {
                    Text("Wallet not connected",
                        color = MaterialTheme.colorScheme.secondary)
                }
                is WalletState.Success -> {
                    Text("Wallet connected!",
                        color = MaterialTheme.colorScheme.primary)
                }
                is WalletState.Error -> {
                    val errorState = walletState.value as WalletState.Error
                    Timber.tag("Wallet Error").e(errorState.message.toString())
                    Text("Wallet Error!: ${errorState.message} ",
                        color = MaterialTheme.colorScheme.primary)
                }
                WalletState.Loading -> {
                    Text("Wallet Loading!",
                        color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

