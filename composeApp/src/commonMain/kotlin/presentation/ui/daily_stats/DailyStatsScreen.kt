package presentation.ui.daily_stats

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import presentation.component.day_overview.DayOverview
import presentation.icon.CalendarMonthIcon
import presentation.ui.home.dualCore1
import java.time.LocalDate
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
//    var selectedDay by remember { mutableStateOf(LocalDate.now()) }
    val state by viewModel.state.collectAsState()

    /**
     * Update week days to display on the stripe
     */
    fun updateWeek(selectedDate: LocalDate): List<LocalDate> {
        // if new week on day selection - refresh week info
        // set selected day in the middle of the stripe
        val displayDays = ((3 downTo 0).map { selectedDate.minusDays(it.toLong()) }).toMutableList()
        displayDays.add(selectedDate.plusDays(1L))
        displayDays.add(selectedDate.plusDays(2L))
        displayDays.add(selectedDate.plusDays(3L))

        return displayDays
    }

    Surface {
        Column(modifier = Modifier.fillMaxWidth()) {
            CalendarTop(
                onTodayClick = { viewModel.updateReports(LocalDate.now()) },
                state.selectedDay
            )
            Spacer_8dp()
            CalendarWeek(selectedDate = state.selectedDay,
                days = updateWeek(LocalDate.now()),
                onDaySelected = { newDay -> viewModel.updateReports(newDay) })

            DayOverview(
                baseSegments = dualCore1.segments,
                realSegments = state.dayReports
            )


//            if (state.dayReports.isNotEmpty()) {
//
//
//                DayOverview(
//                    baseSegments = dualCore1.segments,
//                    realSegments = state.dayReports
//                )
//
//
//            } else {
//                Row(
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Icon(
//                        NoDataIcon,
//                        null,
//                        tint = MaterialTheme.colorScheme.primary,
//                        modifier = Modifier.size(280.dp).padding(top = 80.dp)
//                    )
//                }
//            }
        }
    }
}


@Composable
fun CalendarTop(onTodayClick: () -> Unit, date: LocalDate) {
    // Screen top description
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
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
                    CalendarMonthIcon, null, modifier = Modifier.size(35.dp)
                )
                Spacer_12dp()
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        (date.month.getDisplayName(
                            TextStyle.FULL_STANDALONE, Locale.getDefault()
                        )).replaceFirstChar { it.uppercase() },
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
        if (date != LocalDate.now()) {
            Surface(
                onClick = onTodayClick
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(5.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        painter = painterResource(Res.drawable.redo),
                        contentDescription = "Today",
                    )
                    Spacer_16dp()
                    Text(
                        text = "Today",
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}


@Composable
fun CalendarDay(
    activeDay: LocalDate,
    onDaySelected: (selectedDay: LocalDate) -> Unit,
    displayDay: LocalDate,
) {

    // Calendar stripe's one day block
    Surface(
        onClick = { onDaySelected(displayDay) },
        shape = MaterialTheme.shapes.medium,
        color = if (activeDay == displayDay) {
            Color.Blue
        } else {
            MaterialTheme.colorScheme.surface
        },
        modifier = Modifier.size(height = 75.dp, width = 48.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                displayDay.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                color = if (activeDay == displayDay) {
                    Color.White
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = displayDay.format(DateTimeFormatter.ofPattern("dd")),
                style = MaterialTheme.typography.bodyLarge,
                color = if (activeDay == displayDay) {
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
fun CalendarWeek(
    onDaySelected: (selectedDay: LocalDate) -> Unit, selectedDate: LocalDate, days: List<LocalDate>
) {
    Row(
        modifier = Modifier.padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        days.forEach { day ->
            CalendarDay(
                selectedDate, displayDay = day, onDaySelected = onDaySelected
            )
        }
    }
}
