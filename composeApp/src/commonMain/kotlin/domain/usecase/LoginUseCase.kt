package domain.usecase

import data.network.DataState
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    fun invoke(
        email: String,
        password: String,
    ): Flow<DataState<String>>
}
