package com.example.userpaytracker

import android.app.Application
import com.example.userpaytracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class UserPayTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UserPayTrackerApplication)
            modules(appModule)
        }
    }
}
