package di

import data.PreferenceManager
import data.network.KtorHttpClient
import data.network.auth.AuthService
import data.network.auth.AuthServiceImpl
import data.repository.SettingsRepositoryImpl
import data.usecase.LoginUseCaseImpl
import domain.repository.SettingsRepository
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import presentation.ui.login.LoginScreenModel

fun commonModule() = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single {
        KtorHttpClient.httpClient(
            settingsRepository = get()
        )
    }


    single<AuthService> {
        AuthServiceImpl(
            httpClient = get()
        )
    }


    /**
     * Interactors
     */
    single {
        LoginUseCaseImpl(
            authService = get(),
            preferencesRepository = get()
        )
    }

    /**
     * ViewModels
     */
    factory {
        LoginScreenModel()
    }

    /**
     * Multiplatform-Settings
     */
    single<PreferenceManager> {
        PreferenceManager(settings = get())
    }

    /**
     * Repositories
     */
    single<SettingsRepository> {
        SettingsRepositoryImpl(
            preferenceManager = get(),
        )
    }
}


