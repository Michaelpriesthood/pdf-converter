package com.techmedia.pdfconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        @Suppress("DEPRECATION")
        Handler().postDelayed({
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }, 3000) // 3000 is the delayed time in milliseconds.

    }
}