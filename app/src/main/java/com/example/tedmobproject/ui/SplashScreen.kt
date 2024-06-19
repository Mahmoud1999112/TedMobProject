// SplashScreen.kt

package com.example.tedmobproject.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.tedmobproject.R
import com.example.tedmobproject.helpers.DataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        dataStoreManager = DataStoreManager(applicationContext)

        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginStatus()
        }, 1500)
    }

    private fun checkLoginStatus() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreManager.userCredentials.collect { userCredentials ->
                if (userCredentials != null && userCredentials.email.isNotEmpty() && userCredentials.password.isNotEmpty()) {
                    // User is logged in, navigate to MainActivity
                    startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                } else {
                    // User is not logged in, navigate to LoginActivity
                    startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
                }
                finish()
            }
        }
    }
}
