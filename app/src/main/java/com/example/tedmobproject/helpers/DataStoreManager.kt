// DataStoreManager.kt

package com.example.tedmobproject.helpers

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(context: Context) {
    private val dataStore = context.dataStore

    // Function to save user credentials
    suspend fun saveCredentials(email: String, password: String) {
        dataStore.edit { preferences ->
            preferences[KEY_EMAIL] = email
            preferences[KEY_PASSWORD] = password
        }
    }

    // Function to retrieve user credentials
    val userCredentials: Flow<UserCredentials?> = dataStore.data
        .map { preferences ->
            val email = preferences[KEY_EMAIL] ?: ""
            val password = preferences[KEY_PASSWORD] ?: ""
            if (email.isNotEmpty() && password.isNotEmpty()) {
                UserCredentials(email, password)
            } else {
                null
            }
        }

    companion object {
        private val KEY_EMAIL = stringPreferencesKey("email")
        private val KEY_PASSWORD = stringPreferencesKey("password")
    }
}

data class UserCredentials(val email: String, val password: String)
