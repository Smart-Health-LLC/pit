package com.smarthealth.pit

import android.app.Application
import di.KoinManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

// For Android we need to start koin a bit differently since most of the time Android requires
// Context as a dependency to initialize and access platform-specific APIs. Because of that, we
// will create an Application class in androidMain and initialize koin passing the androidContext()
class KmpApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KmpApplication)
            androidLogger()
            modules(KoinManager.koinModules())
        }
    }
}