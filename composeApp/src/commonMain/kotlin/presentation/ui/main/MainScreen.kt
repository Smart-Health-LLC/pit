package presentation.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.*
import presentation.component.MainNavigationRail

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
                            MainTabs.DailyStatsTab,
//                            MainTabs.AdaptationStatsTab,
                            MainTabs.SettingsTab,
                        ),
                    )
//                    CurrentScreen()
                    CurrentTab()
                }
            } else {
                Scaffold(
                    content = { innerPadding ->
                        Surface(
                            modifier = Modifier
                                .padding(innerPadding)
                                .padding(top = 15.dp)
                                .padding(horizontal = 15.dp)
                        ) {
//                            CurrentScreen()
                            CurrentTab()
                        }
                    },
                    bottomBar = {
                        NavigationBar()
                        {
                            TabNavigationItem(MainTabs.HomeTab)
//                            TabNavigationItem(MainTabs.AdaptationStatsTab)
                            TabNavigationItem(MainTabs.DailyStatsTab)
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
        label = {
            Text(
                // I can't define labels in TabObject because in that case no recomposition happens

                // @formatter:off
                text = when (tab.options.index) {
                    MainTabs.HomeTab.INDEX            -> strings.tabHome
                    MainTabs.AdaptationStatsTab.INDEX -> strings.tabAdaptationStats
                    MainTabs.DailyStatsTab.INDEX      -> strings.tabDailyStats
                    MainTabs.SettingsTab.INDEX        -> strings.tabMore
                    else -> error("Unknown tab")
                },
                // @formatter:on
                fontWeight = (if (isSelected) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                })
            )
        }
    )
}
