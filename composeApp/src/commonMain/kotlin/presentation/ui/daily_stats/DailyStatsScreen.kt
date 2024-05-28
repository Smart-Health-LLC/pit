package presentation.ui.daily_stats

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import presentation.component.*
import presentation.icon.CalendarMonthIcon
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
//     Screen content holder
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {

        CalendarTop(LocalDate.now())
        Spacer_32dp()
        CalendarWeek()

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

        // Selected date indication
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
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

        // To today text button
        Text(
            text = "Today",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Composable
fun CalendarDay(isActive: Boolean, day: LocalDate) {
    // Calendar stripe's one day block
    Surface(
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
                    MaterialTheme.colorScheme.secondary
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
        CalendarDay(true, LocalDate.of(2024, 5, 1))
        CalendarDay(false, LocalDate.of(2024, 5, 2))
        CalendarDay(false, LocalDate.of(2024, 5, 3))
        CalendarDay(false, LocalDate.of(2024, 5, 4))
        CalendarDay(false, LocalDate.of(2024, 5, 5))
        CalendarDay(false, LocalDate.of(2024, 5, 6))
        CalendarDay(true, LocalDate.of(2024, 5, 7))
    }
}
