package di

import com.smarthealth.pit.database.PitDatabase
import data.local.PreferenceManager
import data.network.KtorHttpClient
import data.network.auth.AuthService
import data.network.auth.AuthServiceImpl
import data.repository.*
import data.usecase.LoginUseCaseImpl
import domain.repository.*
import domain.usecase.LoginUseCase
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import platform.LocalDatabaseDriverFactory
import presentation.ui.change_schedule.ChangeScheduleViewModel
import presentation.ui.daily_stats.DailyStatsViewModel
import presentation.ui.home.HomeViewModel
import presentation.ui.main.MainViewModel
import presentation.ui.rate.RateSegmentViewModel
import presentation.ui.settings.SettingsViewModel

fun commonModule() = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }

    /**
     * Database
     */
    single<PitDatabase> {
        PitDatabase(
            driver = get<LocalDatabaseDriverFactory>().create(),
        )
    }

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
     * UseCases
     */
    single<LoginUseCase> {
        LoginUseCaseImpl(
            authService = get(),
            preferencesRepository = get()
        )
    }

    /**
     * View models
     */
    single<MainViewModel> {
        MainViewModel(
            settingsRepository = get(),
            reportRepository = get()
        )
    }

    factory<DailyStatsViewModel> {
        DailyStatsViewModel(segmentReportRepository = get())
    }

    factory<SettingsViewModel> {
        SettingsViewModel(
            settingsRepository = get(),
        )
    }

    factory<ChangeScheduleViewModel> {
        ChangeScheduleViewModel()
    }


    factory<HomeViewModel> {
        HomeViewModel(get())
    }


    single<RateSegmentViewModel> { params ->
        RateSegmentViewModel(segmentReportRepository = get(), initialState = params.get())
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
        SettingsRepositoryImpl(preferenceManager = get())
    }

    single<SegmentReportRepository> {
        SegmentReportRepositoryImpl(
            pitDatabase = get()
        )
    }

    single<ActiveScheduleRepository> {
        ActiveScheduleRepositoryImpl(pitDatabase = get())
    }
}


