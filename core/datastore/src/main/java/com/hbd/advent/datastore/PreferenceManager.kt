package com.hbd.advent.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "advent")

class PreferenceManager(
    @ApplicationContext private val context: Context
){
    suspend fun setUserToken(token: String) {
        context.dataStore.edit {
            it[PreferenceKeys.token] = token
        }
    }

    val userToken: Flow<String?> = context.dataStore.data.map {
        it[PreferenceKeys.token]
    }
}