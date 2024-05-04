package presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class InitialNavDestination(
    val route: String, val arguments: List<NamedNavArgument>
) {
    data object Splash : InitialNavDestination(route = "Splash", arguments = emptyList())
    data object Main : InitialNavDestination(route = "Main", arguments = emptyList())
}
