package com.route.todo_application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}