package presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import pit.composeapp.generated.resources.*
import presentation.ui.home.HomeScreen
import presentation.ui.settings.SettingsScreen


@OptIn(ExperimentalResourceApi::class)
@Composable
fun FilledIcon(item: Tab) = when (item.options.index) {
    MainTabs.HomeTab.INDEX -> painterResource(Res.drawable.ic_space_dashboard_filled_24px)
    MainTabs.StatisticsTab.INDEX -> painterResource(Res.drawable.ic_data_exploration_filled_24px)
    MainTabs.NotesTab.INDEX -> painterResource(Res.drawable.ic_note_alt_filled_24px)
    MainTabs.SettingsTab.INDEX -> painterResource(Res.drawable.ic_person_filled_24px)
    // nonsense
    else -> painterResource(Res.drawable.ic_space_dashboard_filled_24px)
}

internal sealed class MainTabs {
    internal object HomeTab : Tab {
        const val INDEX: UShort = 0u

        @OptIn(ExperimentalResourceApi::class)
        override val options: TabOptions
            @Composable
            get() {
                val title = "Home"
                val icon = painterResource(Res.drawable.space_dashboard_24px)

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
            HomeScreen("Home screen")
        }
    }


    internal object StatisticsTab : Tab {
        const val INDEX: UShort = 1u

        override val options: TabOptions
            @Composable
            get() {
                val title = "Statistics"
                val icon = rememberVectorPainter(Icons.Default.Star)

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
            HomeScreen(strings.simple)
        }
    }


    internal object NotesTab : Tab {

        const val INDEX: UShort = 2u

        override val options: TabOptions
            @Composable
            get() {
                val title = "Notes"
                val icon = rememberVectorPainter(Icons.Outlined.DateRange)

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
            HomeScreen("Calendar screen")
        }
    }

    internal object SettingsTab : Tab {
        const val INDEX: UShort = 3u

        override val options: TabOptions
            @Composable
            get() {
                val title = "Settings"
                val icon = rememberVectorPainter(Icons.Default.Settings)

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
            SettingsScreen()
        }
    }
}
