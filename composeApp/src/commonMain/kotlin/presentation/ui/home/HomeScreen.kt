package presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dokar.sonner.rememberToasterState
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import pit.composeapp.generated.resources.*
import presentation.component.*
import presentation.extention.toFancyString
import presentation.icon.AchievementIcon
import presentation.theme.Inter
import presentation.ui.change_schedule.ChangeScheduleScreen
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class HomeScreen() : Screen {

    @Composable
    override fun Content() {
        HomeScreenContent()
    }
}

@Composable
fun HomeScreenContent(viewModel: HomeViewModel = koinInject()) {
    val localNavigator = LocalNavigator.currentOrThrow

    val schedule = viewModel.state.collectAsState().value

    val homeState = viewModel.newState.collectAsState().value

    val reportsToday = viewModel.reports.collectAsState().value

    val toasterState = rememberToasterState { }
    ToasterWrapper(toasterState)
    val toasterMessage = strings.inDevelopment

    val updateIntervalMinutes = 1L
    LaunchedEffect(updateIntervalMinutes) {
        while (true) {
            delay(updateIntervalMinutes * 60 * 1000) // Convert minutes to milliseconds
            viewModel.updateNapIn(schedule)
        }
    }


    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    localNavigator.push(ChangeScheduleScreen())
                },
            ) {
                Icon(Icons.Filled.Edit, "Floating action button.")
            }
        },
        topBar = {
            Surface {

                // High profile block
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Today is the day yaaaaah
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = (LocalDate.now().dayOfWeek.getDisplayName(
                                TextStyle.FULL,
                                Locale.getDefault()
                            )).replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.W500
                        )
                        Text(
                            LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE),
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.LightGray,
                            modifier = Modifier.padding(start = 3.dp)
                        )
                    }

                    // Motivation button
                    IconButton(
                        onClick = {
                            toasterState.show(toasterMessage)
                        },
                        enabled = true
                    ) {
                        Icon(AchievementIcon, null, modifier = Modifier.size(25.dp))
                    }

                }
            }
        }
    ) {

        // Screen content holder
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(it)
        ) {
            item {
                // Current schedule visual scheme
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    ScheduleComponent(schedule.segments, 350, 170f, showCurrentTime = true)
                }
                Spacer_24dp()
            }

            item {
                // Short info about schedule
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Everyman",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.W500
                    )
                    Text(
                        strings.startedAt + LocalDate.now().minusMonths(1)
                            .format(DateTimeFormatter.ISO_LOCAL_DATE),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.LightGray,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
                Spacer_24dp()
            }

            item {
                // Quick info in numbers
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(shape = MaterialTheme.shapes.medium)
                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                        .padding(vertical = 16.dp, horizontal = 18.dp)
                        .fillMaxWidth()
                ) {
                    InfoUnit(
                        Res.drawable.ic_clear_night_filled_24px,
                        strings.napIn,
                        homeState.napIn.toFancyString(),
                        Color.Blue
                    )


                    // yeah thats crap
                    val tstToday = Duration.ZERO
                    reportsToday.filter { it.day == LocalDate.now() }.forEach {
                        tstToday.plus(

                            // todo really good to have that in separate function
                            if (!it.start.isBefore(it.end)) {
                                Duration.between(it.start, LocalTime.MAX)
                                    .plus(
                                        Duration.between(LocalTime.MIN, it.end)
                                    ).plusMinutes(1)
                            } else {
                                Duration.between(it.start, it.end)
                            }

                        )
                    }

                    InfoUnit(
                        Res.drawable.ic_hourglass_filled_24px,
                        strings.tstToday,
                        tstToday.toFancyString(),
                        Color(0xffe08a1a)
                    )
                    InfoUnit(
                        Res.drawable.ic_whatshot_filled_24px,
                        strings.streak,
                        "3 days",
                        Color.Red
                    )
                }

                Spacer_16dp()
            }


            item {
                GraphWrapper()

                Spacer_32dp()
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    DaySleepOverviewComponent()
                }
                Spacer_24dp()
                Spacer_24dp()
            }


            // Action buttons

            // button - edit schedule
//        LazyVerticalGrid(
//            columns = GridCells.Adaptive(minSize = 128.dp),
//            contentPadding = PaddingValues(10.dp),
//            verticalArrangement = Arrangement.spacedBy(10.dp),
//            horizontalArrangement = Arrangement.spacedBy(10.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            item {
//                // change schedule
//                Button(
//                    onClick = {
//                        localNavigator.push(ChangeScheduleScreen())
//                    }, modifier = Modifier.height(56.dp).wrapContentWidth()
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.Edit,
//                        contentDescription = null,
//                        modifier = Modifier.size(ButtonDefaults.IconSize)
//                    )
//                    Spacer_12dp()
//                    Text(text = strings.changeSchedule)
//                }
//
//            }

//            item {
//
//                // track a tiredness bomb
//                val toastMessage = strings.inDevelopment
//                Button(
//                    onClick = {
//                        toasterState.show(message = toastMessage)
//                    }, modifier = Modifier.height(56.dp)
//                ) {
//                    Icon(
//                        imageVector = BombIcon,
//                        contentDescription = null,
//                        modifier = Modifier.size(ButtonDefaults.IconSize)
//                    )
//                    Spacer_8dp()
//                    Text(text = "Set tiredness bomb")
//                }
//            }
//        }
        }
    }
}

@Composable
fun GraphWrapper() {

    // Graph element
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(vertical = 20.dp)
        ) {

            // graph description
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    textAlign = TextAlign.Start,
                    text = strings.graphEase,
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            SmoothLineGraph(Modifier.height(240.dp))
            Spacer_4dp()
            // Custom graph legend
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                GraphLegendItem(strings.wakeUpEase, Color.Green, 15.dp)
                Spacer_16dp()
                GraphLegendItem(strings.fallAsleepEase, Color.Magenta, 15.dp)
            }
        }
    }

}


@Composable
fun InfoUnit(icon: DrawableResource, title: String, value: String, iconColor: Color) {
    Typography().bodyLarge
    Column(horizontalAlignment = Alignment.Start) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                tint = iconColor,
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(title, style = MaterialTheme.typography.labelLarge)
        }
        Text(
            value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontFamily = Inter()
        )
    }
}