package di

import org.koin.dsl.module
import presentation.ui.entry.view_model.AuthViewModel

fun commonModule() = module {
    single<AuthViewModel> {
        AuthViewModel()
    }
}


