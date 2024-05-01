package di

import org.koin.core.component.KoinComponent
import org.koin.core.module.Module

class KoinManager : KoinComponent {
    fun getKoinInstance() = getKoin()

    companion object {
        fun startKoin() {
            org.koin.core.context.startKoin {
                modules(koinModules())
            }
        }

        fun koinModules(): List<Module> {
            return listOf()
        }
    }
}