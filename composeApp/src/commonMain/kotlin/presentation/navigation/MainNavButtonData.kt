package presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainNavButtonData(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
) {

    data object Home : MainNavButtonData(
        route = "Home", title = "Home",
        selectedIcon = Icons.Filled.Create,
        unSelectedIcon = Icons.Outlined.Create
    )

    data object Wishlist : MainNavButtonData(
        route = "Wishlist", title = "Wishlist",
        selectedIcon = Icons.Filled.Check,
        unSelectedIcon = Icons.Outlined.Check
    )

    data object Cart : MainNavButtonData(
        route = "Cart", title = "Cart",
        selectedIcon = Icons.Filled.Edit,
        unSelectedIcon = Icons.Outlined.Edit
    )

    data object Profile : MainNavButtonData(
        route = "Profile", title = "Profile",
        selectedIcon = Icons.Filled.Menu,
        unSelectedIcon = Icons.Outlined.Menu,
    )


}
