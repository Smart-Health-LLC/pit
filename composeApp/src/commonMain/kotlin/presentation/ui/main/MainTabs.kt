package presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.painterResource
import pit.composeapp.generated.resources.*
import presentation.ui.adaptation_stats.AdaptationStatsScreenContent
import presentation.ui.daily_stats.DailyStatsScreenContent
import presentation.ui.home.HomeScreen
import presentation.ui.settings.SettingsScreen

/**
 * Data for navigation tabs
 * Note: can't use here strings.* because in that case no recomposition on tabs' labels happens
 */

// @formatter:off
@Composable
fun FilledIcon(item: Tab) = when (item.options.index) {
    MainTabs.HomeTab.INDEX              -> painterResource(Res.drawable.ic_space_dashboard_filled_24px)
    MainTabs.AdaptationStatsTab.INDEX   -> painterResource(Res.drawable.ic_data_exploration_filled_24px)
    MainTabs.DailyStatsTab.INDEX        -> painterResource(Res.drawable.ic_note_alt_filled_24px)
    MainTabs.SettingsTab.INDEX          -> painterResource(Res.drawable.ic_more_horiz_24px)
    else -> error("Unknown tab")
}
// @formatter:on

sealed class MainTabs {
    object HomeTab : Tab {
        private fun readResolve(): Any = HomeTab
        const val INDEX: UShort = 0u

        override val options: TabOptions
            @Composable
            get() {
                val title = "Home"
                val icon = painterResource(Res.drawable.ic_data_exploration_24px)

                return remember {
                    TabOptions(
                        index = INDEX,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
            Navigator(HomeScreen())
        }
    }


    object AdaptationStatsTab : Tab {
        private fun readResolve(): Any = AdaptationStatsTab
        const val INDEX: UShort = 1u

        override val options: TabOptions
            @Composable
            get() {
                val title = "Adaptation"
                val icon = painterResource(Res.drawable.ic_data_exploration_24px)

                return remember {
                    TabOptions(
                        index = INDEX,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
            AdaptationStatsScreenContent()
        }
    }


    object DailyStatsTab : Tab {
        private fun readResolve(): Any = DailyStatsTab

        const val INDEX: UShort = 2u

        override val options: TabOptions
            @Composable
            get() {
                val title = "DailyStats"
                val icon = painterResource(Res.drawable.ic_note_alt_24px)

                return remember {
                    TabOptions(
                        index = INDEX,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
            DailyStatsScreenContent()
        }
    }

    object SettingsTab : Tab {
        private fun readResolve(): Any = SettingsTab
        const val INDEX: UShort = 3u

        override val options: TabOptions
            @Composable
            get() {
                val title = "Settings"
                val icon = painterResource(Res.drawable.ic_more_horiz_24px)

                return remember {
                    TabOptions(
                        index = INDEX,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
            Navigator(SettingsScreen())
        }
    }
}
