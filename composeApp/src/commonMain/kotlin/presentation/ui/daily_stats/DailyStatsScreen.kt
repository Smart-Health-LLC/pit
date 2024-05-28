package presentation.ui.daily_stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject

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

        // Screen top description
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                "Adaptation overview",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.W500
            )
        }

        // Calendar stripe
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            // Calendar stripe's one day block
            val dayChipWidth = 60.dp
            val bottomDayChipPadding = 20.dp
            val dayChipPadding = 5.dp
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Color.Blue)
                    .size(height = dayChipWidth * 2, width = dayChipWidth)
                    .padding(
                        start = dayChipPadding,
                        top = dayChipPadding,
                        end = dayChipPadding,
                        bottom = bottomDayChipPadding
                    )
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .size(dayChipWidth - dayChipPadding * 2)
                        .align(Alignment.TopCenter)
                ) {
                    Text(
                        text = "28",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text("Sat", color = Color.White, modifier = Modifier.align(Alignment.BottomCenter))
            }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Color.Blue)
                    .size(height = dayChipWidth * 2, width = dayChipWidth)
                    .padding(
                        start = dayChipPadding,
                        top = dayChipPadding,
                        end = dayChipPadding,
                        bottom = bottomDayChipPadding
                    )
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .size(dayChipWidth - dayChipPadding * 2)
                        .align(Alignment.TopCenter)
                ) {
                    Text(
                        text = "28",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text("Sat", color = Color.White, modifier = Modifier.align(Alignment.BottomCenter))
            }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Color.Blue)
                    .size(height = dayChipWidth * 2, width = dayChipWidth)
                    .padding(
                        start = dayChipPadding,
                        top = dayChipPadding,
                        end = dayChipPadding,
                        bottom = bottomDayChipPadding
                    )
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .size(dayChipWidth - dayChipPadding * 2)
                        .align(Alignment.TopCenter)
                ) {
                    Text(
                        text = "28",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text("Sat", color = Color.White, modifier = Modifier.align(Alignment.BottomCenter))
            }


            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Color.Blue)
                    .size(height = dayChipWidth * 2, width = dayChipWidth)
                    .padding(
                        start = dayChipPadding,
                        top = dayChipPadding,
                        end = dayChipPadding,
                        bottom = bottomDayChipPadding
                    )
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .size(dayChipWidth - dayChipPadding * 2)
                        .align(Alignment.TopCenter)
                ) {
                    Text(
                        text = "28",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text("Sat", color = Color.White, modifier = Modifier.align(Alignment.BottomCenter))
            }

        }

        // Chart section name
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(vertical = 15.dp)
        ) {
            Text(
                "Adaptation process",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        // chart here


        // Summary block
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(vertical = 20.dp, horizontal = 10.dp),
//            horizontalArrangement = Arrangement.Center
        ) {
            // Summary column
            Column(modifier = Modifier.padding(end = 55.dp)) {

                // One summary block
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {
                        Icon(
                            Icons.Rounded.Notifications, contentDescription = "ash", modifier =
                            Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Column {
                        Text(
                            text = "In bed",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "23.33",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


                // One summary block
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {
                        Icon(
                            Icons.Rounded.Notifications, contentDescription = "ash", modifier =
                            Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Column {
                        Text(
                            text = "In bed",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "23.33",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // One summary block
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {
                        Icon(
                            Icons.Rounded.Notifications, contentDescription = "ash", modifier =
                            Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Column {
                        Text(
                            text = "In bed",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "23.33",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }


            // Summary column
            Column(modifier = Modifier.padding(end = 25.dp)) {

                // One summary block
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {
                        Icon(
                            Icons.Rounded.Notifications, contentDescription = "ash", modifier =
                            Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Column {
                        Text(
                            text = "In bed",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "23.33",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


                // One summary block
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {
                        Icon(
                            Icons.Rounded.Notifications, contentDescription = "ash", modifier =
                            Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Column {
                        Text(
                            text = "In bed",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "23.33",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // One summary block
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {
                        Icon(
                            Icons.Rounded.Notifications, contentDescription = "ash", modifier =
                            Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Column {
                        Text(
                            text = "In bed",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "23.33",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
        }
    }
}