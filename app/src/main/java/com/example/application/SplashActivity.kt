package com.example.application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = getSharedPreferences("user_account", Context.MODE_PRIVATE)

        // il n'a pas un compte
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val newIntent = Intent(application, MainActivity::class.java)
            startActivity(newIntent)
            finish()
        }, 2000)

    }
}