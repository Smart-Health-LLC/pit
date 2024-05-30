package presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import pit.composeapp.generated.resources.*
import presentation.component.*
import presentation.icon.BombIcon
import presentation.ui.change_schedule.ChangeScheduleScreen
import presentation.ui.no_internet.NoInternetScreen
import java.time.LocalDate
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
                    text = LocalDate.now().dayOfWeek.getDisplayName(
                        TextStyle.FULL,
                        Locale.getDefault()
                    ),
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
            val parentNavigator = LocalNavigator.currentOrThrow
            parentNavigator.replaceAll(NoInternetScreen())
        }


        // Current schedule visual scheme
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp), contentAlignment = Alignment.Center
        ) {
            ScheduleComponent(schedule, 350, 170f)
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
                "01:18",
                Color.Blue
            )
            InfoUnit(
                Res.drawable.ic_hourglass_filled_24px,
                strings.tstToday,
                "04:16",
                Color(0xffe08a1a)
            )
            InfoUnit(Res.drawable.ic_whatshot_filled_24px, strings.streak, "3 days", Color.Red)
        }


        // Action buttons

        val toasterState = rememberToasterState()

        ToasterWrapper(toasterState)


        // button - edit schedule
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // change schedule
            Button(onClick = {
                localNavigator.push(ChangeScheduleScreen(schedule, viewModel))
            }, modifier = Modifier.height(56.dp)) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Text(text = strings.changeSchedule)
            }

            // track a tiredness bomb
            Button(
                onClick = {
                    toasterState.show(message = "Not supported yet")
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
            fontWeight = FontWeight.Bold
        )
    }
}