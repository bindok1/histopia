package com.apps.histopia.utils.storage

import kotlinx.serialization.Serializable

@Serializable
data class Pairing(
    val walletId: String,
    val walletName: String,
    val address: String,
    val chainId: Int,
    val timestamp: Long = System.currentTimeMillis()
)


