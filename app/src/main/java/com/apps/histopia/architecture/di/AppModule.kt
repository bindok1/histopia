package com.apps.histopia.architecture.di

import android.content.Context
import com.apps.histopia.architecture.features.wallet.walletService.WalletService
import com.apps.histopia.utils.storage.StorageUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideStorageUtils(@ApplicationContext context: Context): StorageUtils {
        return StorageUtils(context)
    }



    @Provides
    @Singleton
    fun provideWalletService(storageUtils: StorageUtils): WalletService {
        return WalletService(storageUtils)
    }
}