package data.usecase

import business.DataState
import business.ProgressBarState
import domain.repository.SettingsRepository
import domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import wtf.auth.AuthService

class LoginUseCaseImpl(
    private val authService: AuthService,
    private val preferencesRepository: SettingsRepository,
) : LoginUseCase {
    override fun invoke(
        email: String,
        password: String,
    ): Flow<DataState<String>> = flow {
        try {
            // start loading
            emit(DataState.Loading(progressBarState = ProgressBarState.ButtonLoading))

            val apiResponse = authService.login(email, password)
            val responseResult = apiResponse.result

            if (responseResult != null) {
                // todo save token
//                preferencesRepository.saveToken(responseResult)
            }

            // emit resulting data
            emit(DataState.Data(responseResult, apiResponse.status))
        } catch (e: Exception) {
            e.printStackTrace()

        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}
