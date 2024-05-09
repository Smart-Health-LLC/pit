package di

import org.koin.core.module.Module
import org.koin.dsl.module
import platform.SettingsFactory

actual fun platformModule(): Module = module {
    single { SettingsFactory().createSettings() }
}