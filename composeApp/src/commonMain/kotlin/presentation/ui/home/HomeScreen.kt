package presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import domain.model.Schedule
import domain.model.Segment
import org.jetbrains.compose.resources.*
import pit.composeapp.generated.resources.*
import presentation.component.ScheduleComponent
import java.time.LocalTime


val dualCore1 = Schedule(
    name = "Dual Core 1",
    tst = LocalTime.of(5, 20),
    segments = listOf(
        Segment(
            LocalTime.of(21, 30),
            LocalTime.of(0, 50)
        ),
        Segment(
            LocalTime.of(5, 50),
            LocalTime.of(7, 30)
        ),

        Segment(
            LocalTime.of(14, 0),
            LocalTime.of(14, 20)
        )
    )
)


val everyman1 = Schedule(
    name = "Everyman 1",
    tst = LocalTime.of(6, 20),
    segments = listOf(
        Segment(
            LocalTime.of(23, 0),
            LocalTime.of(5, 0)
        ),
        Segment(
            LocalTime.of(13, 0),
            LocalTime.of(13, 25)
        )
    )
)


val siesta = Schedule(
    name = "Siesta",
    tst = LocalTime.of(6, 30),
    segments = listOf(
        Segment(
            LocalTime.of(23, 0),
            LocalTime.of(4, 0)
        ),
        Segment(
            LocalTime.of(13, 0),
            LocalTime.of(14, 30)
        )
    )
)


val segmented = Schedule(
    name = "Segmented",
    tst = LocalTime.of(7, 0),
    segments = listOf(
        Segment(
            LocalTime.of(22, 0),
            LocalTime.of(1, 30)
        ),

        Segment(
            LocalTime.of(4, 30),
            LocalTime.of(8, 0)
        )
    )
)


val dualCore2 = Schedule(
    name = "Dual Core 2",
    tst = LocalTime.of(5, 10),
    segments = listOf(
        Segment(
            LocalTime.of(22, 0),
            LocalTime.of(1, 0)
        ),
        Segment(
            LocalTime.of(5, 50),
            LocalTime.of(7, 20)
        ),
        Segment(
            LocalTime.of(12, 0),
            LocalTime.of(12, 25)
        ),
        Segment(
            LocalTime.of(16, 0),
            LocalTime.of(16, 25)
        )
    )
)


val everyman2 = Schedule(
    name = "Everyman 2",
    tst = LocalTime.of(5, 10),
    segments = listOf(
        Segment(
            LocalTime.of(23, 0),
            LocalTime.of(3, 30)
        ),
        Segment(
            LocalTime.of(8, 0),
            LocalTime.of(8, 25)
        ),

        Segment(
            LocalTime.of(14, 30),
            LocalTime.of(14, 55)
        )
    )
)


@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeScreen() {
    val containerHorizontalPadding = 15.dp

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
                    "Dual Core 1",
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

            ScheduleComponent(dualCore1, 350, 170f)
        }


        // Fancy button - edit schedule
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.height(56.dp)) {
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
            InfoUnit(Res.drawable.ic_clear_night_filled_24px, strings.napIn, "01:18", Color.Blue)
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