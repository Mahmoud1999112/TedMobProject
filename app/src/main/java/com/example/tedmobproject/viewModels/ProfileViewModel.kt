package com.example.tedmobproject.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tedmobproject.useCases.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val userEmail: Flow<String?> = logoutUseCase.getEmail()


    fun logout() {
        viewModelScope.launch {
            logoutUseCase.logout()
        }
    }


}