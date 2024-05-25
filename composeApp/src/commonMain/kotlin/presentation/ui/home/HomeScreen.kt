package presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import org.jetbrains.compose.resources.*
import org.koin.compose.koinInject
import pit.composeapp.generated.resources.*
import presentation.component.ScheduleComponent
import presentation.ui.changeScheduleScreen.ChangeScheduleScreen

class HomeScreen() : Screen {


    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val containerHorizontalPadding = 15.dp
        val localNavigator = LocalNavigator.currentOrThrow
        val viewModel: HomeViewModel = koinInject()

        val schedule = viewModel.state.collectAsState().value

        // Screen content holder
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = containerHorizontalPadding)
        ) {

            // High profile block
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Short info about schedule
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        schedule.name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.W500
                    )
                    Text(
                        strings.startedAt + "2023/01/23",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.LightGray,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }

                // Profile pic
                Box() {
                    Image(
                        painterResource(Res.drawable.avatar_placeholder),
                        "profile",
                        modifier = Modifier
                            .size(49.dp)
                            .clip(
                                CircleShape
                            )
                    )
                }
            }


            // Current schedule visual scheme
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp), contentAlignment = Alignment.Center
            ) {
                ScheduleComponent(schedule, 350, 170f)
            }


            // Fancy button - edit schedule
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    localNavigator.push(ChangeScheduleScreen(schedule, viewModel))
                }, modifier = Modifier.height(56.dp)) {
                    Text(text = strings.changeSchedule)
                }
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
        }
    }


    @OptIn(ExperimentalResourceApi::class)
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
}