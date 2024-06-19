package com.example.tedmobproject.useCases

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.tedmobproject.helpers.DataStoreManager
import com.example.tedmobproject.helpers.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class LogoutUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getEmail(): Flow<String?> = flow {
        val dataStoreManager = DataStoreManager(context)
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreManager.userCredentials.collect { userCredentials ->
                if (userCredentials != null) {
                    val email = userCredentials.email
                    emit(email)
                }
            }
        }
    }

    suspend fun logout() {
        val dataStore = context.dataStore
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}