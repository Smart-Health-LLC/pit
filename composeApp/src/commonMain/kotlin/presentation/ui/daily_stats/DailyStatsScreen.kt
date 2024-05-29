package presentation.ui.daily_stats

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import pit.composeapp.generated.resources.Res
import pit.composeapp.generated.resources.redo
import presentation.component.*
import presentation.icon.CalendarMonthIcon
import presentation.ui.home.everyman1
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class DailyStatsScreen : Screen {
    @Composable
    override fun Content() {
        DailyStatsScreenContent()
    }
}

@Composable
fun DailyStatsScreenContent(viewModel: DailyStatsViewModel = koinInject()) {
//     Screen content holder

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(18.dp)
//            .scrollable(rememberScrollState(0), orientation = Orientation.Vertical)
    ) {
        item {
            CalendarTop(LocalDate.now())
            Spacer_8dp()
            CalendarWeek()
            StaticScheduleComponent(everyman1, 250, 130f)
            Spacer_8dp()

        }

        item {
            SegmentReportCard(
                LocalTime.of(5, 0),
                LocalTime.of(7, 30),
                3, 4,
                LocalTime.of(3, 28),
                LocalTime.of(8, 43),
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
            )
        }
        item {
            SegmentReportCard(
                LocalTime.of(5, 0),
                LocalTime.of(7, 30),
                3, 4,
                LocalTime.of(3, 28),
                LocalTime.of(8, 43),
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
            )
        }
        item {
            SegmentReportCard(
                LocalTime.of(5, 0),
                LocalTime.of(7, 30),
                3, 4,
                LocalTime.of(3, 28),
                LocalTime.of(8, 43),
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
            )
        }
        item {
            Spacer_32dp()
        }
    }
}


@Composable
fun CalendarTop(date: LocalDate) {
    // Screen top description
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {

        Surface(onClick = {}) {

            // Selected date indication
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(5.dp)
            ) {

                // Calendar icon
                Icon(
                    CalendarMonthIcon,
                    null,
                    modifier = Modifier
                        .size(35.dp)
                )
                Spacer_12dp()
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Spacer_8dp()
                    Text(
                        text = date.year.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }
            }
        }
        // To today text button
        Surface(
            onClick = {
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(Res.drawable.redo),
                    contentDescription = "Today",
                )
                Spacer_16dp()
                Text(
                    text = "Today",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}


@Composable
fun CalendarDay(isActive: Boolean, day: LocalDate) {
    // Calendar stripe's one day block
    Surface(
        onClick = {},
        shape = MaterialTheme.shapes.medium,
        color = if (isActive) {
            Color.Blue
        } else {
            MaterialTheme.colorScheme.surface
        },
        modifier = Modifier
            .size(height = 75.dp, width = 48.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                day.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                color = if (isActive) {
                    Color.White
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = day.format(DateTimeFormatter.ofPattern("dd")),
                style = MaterialTheme.typography.bodyLarge,
                color = if (isActive) {
                    Color.White
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


// Calendar stripe
@Composable
fun CalendarWeek() {
    Row(
        modifier = Modifier.padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        CalendarDay(false, LocalDate.of(2024, 5, 1))
        CalendarDay(false, LocalDate.of(2024, 5, 2))
        CalendarDay(false, LocalDate.of(2024, 5, 3))
        CalendarDay(false, LocalDate.of(2024, 5, 4))
        CalendarDay(true, LocalDate.of(2024, 5, 5))
        CalendarDay(false, LocalDate.of(2024, 5, 6))
        CalendarDay(false, LocalDate.of(2024, 5, 7))
    }
}
