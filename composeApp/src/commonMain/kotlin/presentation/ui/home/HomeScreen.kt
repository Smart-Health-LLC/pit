package presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import domain.model.Schedule
import domain.model.Segment
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import pit.composeapp.generated.resources.Res
import pit.composeapp.generated.resources.avatar_placeholder
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

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeScreen(someContent: String = "Home screen") {
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
                    "Started at 2023/01/23",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
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

            ScheduleComponent(dualCore1)
        }


        // Fancy button - edit schedule
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.height(56.dp)) {
                Text(text = "Change schedule")
            }
        }

        // Quick info in numbers
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(all = 10.dp)
                .fillMaxWidth()
        ) {
            // Info section
            Column(horizontalAlignment = Alignment.Start) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(Icons.Rounded.ThumbUp, contentDescription = "ash", Modifier.size(12.dp))
                    Spacer(modifier = Modifier.size(4.dp))
                    Text("Description", style = MaterialTheme.typography.labelSmall)
                }
                Text(
                    "22.04",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Info section
            Column(horizontalAlignment = Alignment.Start) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(Icons.Rounded.ThumbUp, contentDescription = "ash", Modifier.size(12.dp))
                    Spacer(modifier = Modifier.size(4.dp))
                    Text("Description", style = MaterialTheme.typography.labelSmall)
                }
                Text(
                    "22.04",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Info section
            Column(horizontalAlignment = Alignment.Start) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(Icons.Rounded.ThumbUp, contentDescription = "ash", Modifier.size(12.dp))
                    Spacer(modifier = Modifier.size(4.dp))
                    Text("Description", style = MaterialTheme.typography.labelSmall)
                }
                Text(
                    "22.04",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }
    }
}