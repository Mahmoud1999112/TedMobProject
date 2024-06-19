package com.example.tedmobproject.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tedmobproject.viewModels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                ProfileScreen(viewModel)
            }
        }
    }

    @Composable
    fun ProfileScreen(viewModel: ProfileViewModel) {
        val showLogoutDialog = remember { mutableStateOf(false) }
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "First Name: John")
            Text(text = "Last Name: Doe")
            Text(
                text = "Email: ${viewModel.userEmail}",
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:${viewModel.userEmail}")
                    }
                    startActivity(intent)
                }
            )
            Text(
                text = "Phone: 123-456-7890",
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:1234567890")
                    }
                    startActivity(intent)
                }
            )
            Button(onClick = { showLogoutDialog.value = true }) {
                Text(text = "Logout")
            }
        }

        if (showLogoutDialog.value) {
            LogoutDialog(showDialog = showLogoutDialog) {
                viewModel.logout()
                restartApp()
            }
        }
    }

    @Composable
    fun LogoutDialog(showDialog: MutableState<Boolean>, onConfirm: () -> Unit) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Logout") },
            text = { Text(text = "Are you sure you want to logout?") },
            confirmButton = {
                Button(onClick = {
                    onConfirm()
                    showDialog.value = false
                }) {
                    Text(text = "Logout")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text(text = "Cancel")
                }
            }
        )
    }

    private fun restartApp() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
    }
}