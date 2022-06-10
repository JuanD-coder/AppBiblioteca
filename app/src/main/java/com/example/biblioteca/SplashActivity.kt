package com.example.biblioteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class SplashActivity : AppCompatActivity() {
    
    private val SPLASH_TIME: Long = 3000
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        Handler().postDelayed({
            startActivity((Intent(this,MainActivity::class.java)))
            finish()
        },SPLASH_TIME)

    }
}