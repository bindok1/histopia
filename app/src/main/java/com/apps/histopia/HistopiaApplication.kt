package com.apps.histopia

import android.app.Application
import com.apps.histopia.architecture.di.AppConfig
import com.reown.android.Core
import com.reown.android.CoreClient
import com.reown.android.relay.ConnectionType
import com.reown.appkit.client.AppKit
import com.reown.appkit.client.Modal
import com.reown.appkit.presets.AppKitChainsPresets
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HistopiaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeReownCore()
    }


    private fun initializeReownCore(){
        try {
            val appMetaData = Core.Model.AppMetaData(
                name = "Histopia",
                description = "Histopia Wallet Integration",
                url = AppConfig.APP_URL,
                icons = listOf(AppConfig.APP_URL),
                redirect = AppConfig.REDIRECT_URL
            )

            CoreClient.initialize(
                application = this,
                projectId = AppConfig.PROJECT_ID,
                metaData = appMetaData,
                connectionType = ConnectionType.AUTOMATIC,
                onError = {error-> Timber.e("Initialization error: $error")}

            )
            AppKit.setChains(AppKitChainsPresets.ethChains.values.toList())

            val init = Modal.Params.Init(CoreClient)
            AppKit.initialize(
                init = init,
                onSuccess = {
                    // You might want to handle this differently in Application class
                    // Maybe through a shared preference or another mechanism
                },
                onError = { error ->
                    Timber.e("Initialization error: $error")

                }
            )


        } catch (e: Exception){
            print(e.toString())
        }
    }
}