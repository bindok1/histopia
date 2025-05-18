package com.apps.histopia.utils.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json



class StorageUtils(private val context: Context) {
    companion object {
        private val WALLET_KEY = stringPreferencesKey("wallet_key")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "wallet_store")

    suspend fun getFromStorage(): Pairing? {
        return context.dataStore.data.first()[WALLET_KEY]?.let {
            Json.decodeFromString<Pairing>(it)
        }
    }

    suspend fun saveToStorage(pairing: Pairing) {
        context.dataStore.edit { preferences ->
            preferences[WALLET_KEY] = Json.encodeToString(
                Pairing.serializer(), pairing
            )
        }
    }
}

