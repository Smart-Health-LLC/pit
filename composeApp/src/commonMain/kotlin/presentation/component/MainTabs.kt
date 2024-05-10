package presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import pit.composeapp.generated.resources.Res
import pit.composeapp.generated.resources.home_filled
import presentation.ui.home.HomeScreen


@Composable
@OptIn(ExperimentalResourceApi::class)
fun FilledIcon(item: Tab) = when (item.options.index) {
    MainTabs.HomeTab.index.toUShort() -> rememberVectorPainter(Icons.Filled.Home)
    MainTabs.CalendarTab.index.toUShort() -> rememberVectorPainter(Icons.Filled.DateRange)
    MainTabs.StatisticsTab.index.toUShort() -> rememberVectorPainter(Icons.Filled.Star)
    MainTabs.SettingsTab.index.toUShort() -> rememberVectorPainter(Icons.Filled.Settings)
    // nonsense
    else -> rememberVectorPainter(Icons.Filled.Home)
}

internal sealed class MainTabs {
    internal object HomeTab : Tab {
        const val index = 1

        @OptIn(ExperimentalResourceApi::class)
        override val options: TabOptions
            @Composable
            get() {
                val title = "Home"
                val icon = rememberVectorPainter(Icons.Default.Home)

                return remember {
                    TabOptions(
                        index = 0u,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
            HomeScreen("Home screen")
        }
    }

    internal object CalendarTab : Tab {

        const val index = 2

        @OptIn(ExperimentalResourceApi::class)
        override val options: TabOptions
            @Composable
            get() {
                val title = "Calendar"
                val icon = rememberVectorPainter(Icons.Outlined.DateRange)

                return remember {
                    TabOptions(
                        index = 1u,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
            HomeScreen("Calendar screen")
        }
    }

    internal object StatisticsTab : Tab {
        const val index = 3

        @OptIn(ExperimentalResourceApi::class)
        override val options: TabOptions
            @Composable
            get() {
                val title = "Statistics"
                val icon = rememberVectorPainter(Icons.Default.Star)

                return remember {
                    TabOptions(
                        index = 2u,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
            HomeScreen("Statistics screen")
        }
    }

    internal object SettingsTab : Tab {
        const val index = 4

        @OptIn(ExperimentalResourceApi::class)
        override val options: TabOptions
            @Composable
            get() {
                val title = "Settings"
                val icon = rememberVectorPainter(Icons.Default.Settings)

                return remember {
                    TabOptions(
                        index = 3u,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
            HomeScreen("Settings screen")
        }
    }

    internal data class AddTaskTab(
        val taskId: Int? = null,
        val index: Int = 5
    ) : Tab {

        @OptIn(ExperimentalResourceApi::class)
        override val options: TabOptions
            @Composable
            get() {
                val title = "Add Task"
                val icon = painterResource(Res.drawable.home_filled)

                return remember {
                    TabOptions(
                        index = 4u,
                        title = title,
                        icon = icon,
                    )
                }
            }

        @Composable
        override fun Content() {
            HomeScreen("Add task screen")
        }
    }
}
