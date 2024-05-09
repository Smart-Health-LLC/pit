package business

sealed class ProgressBarState {
    data object ButtonLoading : ProgressBarState()
    data object Idle : ProgressBarState()
}
