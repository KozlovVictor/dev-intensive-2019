package ru.skillbranch.devintensive

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
//        Timber.plant(Timber.DebugTree())
    }
}