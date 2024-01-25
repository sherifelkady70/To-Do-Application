package com.route.todo_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(mainLooper).postDelayed(object : Runnable{
            override fun run() {
               val intent = Intent(this@SplashScreen,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

        },2000)
    }
}