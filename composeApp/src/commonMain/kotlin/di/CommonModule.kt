package di

import org.koin.dsl.module
import presentation.ui.splash.view_model.LoginViewModel

fun commonModule() = module {
    single<LoginViewModel> {
        LoginViewModel()
    }
}


