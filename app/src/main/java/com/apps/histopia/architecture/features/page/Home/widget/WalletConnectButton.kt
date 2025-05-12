package com.apps.histopia.architecture.features.page.Home.widget

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.apps.histopia.architecture.features.wallet.viewModel.WalletViewModel
import com.apps.histopia.architecture.features.wallet.walletService.WalletService
import com.apps.histopia.architecture.features.wallet.walletService.WalletState
import kotlinx.coroutines.launch

@Composable
fun WalletConnectButton(walletViewModel: WalletViewModel) {
    val scope = rememberCoroutineScope()
    val walletState = walletViewModel.walletState.collectAsState().value

    Button(
        onClick = {
            scope.launch {
                walletViewModel.connectWallet()
            }
        },
        enabled = walletState !is WalletState.Loading
    ) {
        when (walletState) {
            is WalletState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White
                )
            }
            is WalletState.Success -> {
                Text("Connected")
            }
            is WalletState.Error -> {
                Text("Connect Wallet")
            }
            is WalletState.Initial -> {
                Text("Connect Wallet")
            }
        }
    }
}