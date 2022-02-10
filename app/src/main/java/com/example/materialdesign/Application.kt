package com.example.materialdesign

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.*
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : Application() {

    override fun onCreate() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(listOf(appModule))
        }
        super.onCreate()
    }
}

fun Context.application(): Application {
    return applicationContext as Application
}