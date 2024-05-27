package di

import org.koin.core.module.Module
import org.koin.dsl.module
import platform.LocalDatabaseDriverFactory
import platform.SettingsFactory

actual fun platformModule(): Module = module {
    single { SettingsFactory().createSettings() }
    single { LocalDatabaseDriverFactory() }
}