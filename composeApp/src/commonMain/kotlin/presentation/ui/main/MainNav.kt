package presentation.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import presentation.navigation.MainNavButtonData

@Composable
fun MainNav(logout: () -> Unit) {
    val navBottomBarController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigationUI(navController = navBottomBarController)
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                startDestination = MainNavButtonData.Home.route,
                navController = navBottomBarController,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = MainNavButtonData.Home.route) {
//                    HomeNav(logout = logout)
                }
                composable(route = MainNavButtonData.Wishlist.route) {
//                    WishlistNav()
                }
                composable(route = MainNavButtonData.Cart.route) {
//                    CartNav()
                }
                composable(route = MainNavButtonData.Profile.route) {
//                    ProfileNav(logout = logout)
                }
            }
        }

    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomNavigationUI(
    navController: NavController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        )
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp
        ) {

            val items = listOf(
                MainNavButtonData.Home,
                MainNavButtonData.Wishlist,
                MainNavButtonData.Cart,
                MainNavButtonData.Profile,
            )
            items.forEach {
                NavigationBarItem(label = { Text(text = it.title) },
                    selected = it.route == currentRoute,
                    icon = {
                        Icon(
                            if (it.route == currentRoute) it.selectedIcon else it.unSelectedIcon,
//                            painterResource(if (it.route == currentRoute) it.selectedIcon else it.unSelectedIcon),
                            it.title
                        )
                    },
                    onClick = {
                        if (currentRoute != it.route) {
                            navController.navigate(it.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    })
            }
        }
    }
}
