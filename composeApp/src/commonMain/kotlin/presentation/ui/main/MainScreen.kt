package presentation.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.*
import presentation.component.*

/**
 * Serves as a container with tab navigation
 */
class MainScreen : Screen {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    override fun Content() {
        val windowSizeClass = calculateWindowSizeClass()
        val useNavRail = windowSizeClass.widthSizeClass > WindowWidthSizeClass.Compact
        TabNavigator(
            MainTabs.HomeTab,
        ) {
            if (useNavRail) {
                Row {
                    MainNavigationRail(
                        tabNavigator = it,
                        navRailItems = listOf(
                            MainTabs.HomeTab,
                            MainTabs.NotesTab,
                            MainTabs.StatisticsTab,
                            MainTabs.SettingsTab,
                        ),
                    )
                    CurrentScreen()
                }
            } else {
                Scaffold(
                    content = { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(innerPadding),
                        ) {
                            CurrentScreen()
                        }
                    },
                    bottomBar = {
                        NavigationBar()
                        {
                            TabNavigationItem(MainTabs.HomeTab)
                            TabNavigationItem(MainTabs.NotesTab)
                            TabNavigationItem(MainTabs.StatisticsTab)
                            TabNavigationItem(MainTabs.SettingsTab)
                        }
                    },
                )
            }
        }
    }
}


@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val isSelected = tabNavigator.current == tab

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let {
                Icon(
                    if (isSelected) {
                        FilledIcon(tab)
                    } else {
                        it
                    },
                    tab.options.title
                )
            }
        },
    )
}
