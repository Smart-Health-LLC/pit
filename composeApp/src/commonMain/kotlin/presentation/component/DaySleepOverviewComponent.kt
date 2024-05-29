package presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import presentation.icon.OversleepIcon
import presentation.icon.UndersleepIcon
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun DaySleepOverviewComponent() {
    OutlinedCard(
        modifier = Modifier
            .size(width = 340.dp, height = 122.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(vertical = 5.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OverViewItem(LocalTime.of(5, 32), "Избыток", OversleepIcon)
            VerticalDivider()
            OverViewItem(LocalTime.of(2, 2), "Недостаток", UndersleepIcon)
        }
    }
}

@Composable
fun OverViewItem(valueTime: LocalTime, valueDescription: String, imageVector: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector,
            null,
            modifier = Modifier.size(35.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer_12dp()

        // value
        Text(valueTime.format(DateTimeFormatter.ofPattern("HH:mm")))
        // description
        Text(text = valueDescription)
    }
}

@Composable
fun VerticalDivider() {
    Row {
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .height(80.dp)
                .width(1.dp)
        )
    }
}

