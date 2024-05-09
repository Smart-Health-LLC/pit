package domain.usecase

import business.DataState
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    fun invoke(
        email: String,
        password: String,
    ): Flow<DataState<String>>
}
