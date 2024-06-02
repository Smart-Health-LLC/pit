package presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import presentation.icon.BombIcon
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

    val updateIntervalMinutes = 1L
    LaunchedEffect(updateIntervalMinutes) {
        while (true) {
            delay(updateIntervalMinutes * 60 * 1000) // Convert minutes to milliseconds
            viewModel.updateNapIn(schedule)
        }
    }


    // Screen content holder
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

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

        }


        // Current schedule visual scheme
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp), contentAlignment = Alignment.Center
        ) {
            ScheduleComponent(schedule.segments, 350, 170f, showCurrentTime = true)
        }


        // Quick info in numbers
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(vertical = 10.dp, horizontal = 18.dp)
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
            InfoUnit(Res.drawable.ic_whatshot_filled_24px, strings.streak, "3 days", Color.Red)
        }

        Spacer_16dp()

        // Action buttons

        val toasterState = rememberToasterState()

        ToasterWrapper(toasterState)

        // button - edit schedule
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                // change schedule
                Button(
                    onClick = {
                        localNavigator.push(ChangeScheduleScreen())
                    }, modifier = Modifier.height(56.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Text(text = strings.changeSchedule)
                }

            }

            item {

                // track a tiredness bomb
                val toastMessage = strings.inDevelopment
                Button(
                    onClick = {
                        toasterState.show(message = toastMessage)
                    }, modifier = Modifier.height(56.dp)
                ) {
                    Icon(
                        imageVector = BombIcon,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer_8dp()
                    Text(text = "Set tiredness bomb")
                }
            }
        }
    }
}


@Composable
fun InfoUnit(icon: DrawableResource, title: String, value: String, iconColor: Color) {
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