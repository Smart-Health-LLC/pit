package di

import org.koin.dsl.module
import presentation.ui.splash.view_model.LoginViewModel

fun commonModule() = module {
    /**
     * Database
     */
    single<LoginViewModel> {
        LoginViewModel()
    }

}


