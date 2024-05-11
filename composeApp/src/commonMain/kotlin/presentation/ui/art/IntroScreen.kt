package presentation.ui.art

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

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
                    "Everyman",
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
                    painterResource(id = R.drawable.avatar_placeholder),
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
            Image(
                painterResource(id = R.drawable.schedule_scheme_placeholder),
                "ash",
                modifier = Modifier.size(350.dp)
            )
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
