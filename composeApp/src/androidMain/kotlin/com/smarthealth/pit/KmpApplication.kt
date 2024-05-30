package com.smarthealth.pit

import android.app.Application
import di.KoinInit
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext

// For Android we need to start koin a bit differently since most of the time Android requires
// Context as a dependency to initialize and access platform-specific APIs. Because of that, we
// will create an Application class in androidMain and initialize koin passing the androidContext()
class KmpApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        KoinInit().init {
            androidContext(androidContext = this@KmpApplication)
        }
    }
}