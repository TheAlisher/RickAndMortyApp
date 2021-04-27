package com.alis.rickandmorty.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreferences(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "dataStore.preferences"
    )

    fun getExampleCounter(): Flow<Int> {
        return context.dataStore.data.map {
            it[DataStorePreferencesKeys.EXAMPLE_COUNTER] ?: 0
        }
    }

    suspend fun incrementExampleCounter(increment: Int) {
        context.dataStore.edit {
            val currentCounterValue = it[DataStorePreferencesKeys.EXAMPLE_COUNTER] ?: 0
            it[DataStorePreferencesKeys.EXAMPLE_COUNTER] = currentCounterValue + increment
        }
    }

    private object DataStorePreferencesKeys {

        private const val DS_EXAMPLE_COUNTER = "example_counter"

        val EXAMPLE_COUNTER = intPreferencesKey(DS_EXAMPLE_COUNTER)
    }
}