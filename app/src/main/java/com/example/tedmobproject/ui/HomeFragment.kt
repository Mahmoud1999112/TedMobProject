package com.example.tedmobproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.tedmobproject.helpers.DataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var dataStoreManager: DataStoreManager
    var email = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataStoreManager = DataStoreManager(requireContext())
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreManager.userCredentials.collect { userCredentials ->
                if (userCredentials != null) {
                    email = userCredentials.email
                }
            }
        }
        val composeView = ComposeView(requireContext()).apply {
            setContent {
                HomeScreen(email = email)
            }
        }
        return composeView
    }

    @Composable
    fun HomeScreen(email: String) {
        Text(text = "Welcome $email")
    }

}

