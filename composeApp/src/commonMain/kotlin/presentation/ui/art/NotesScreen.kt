package presentation.ui.art

//@Preview(showBackground = true)
//@Composable
//fun NotesPreview() {
//
//    Column(
//        modifier = Modifier
//            .padding(end = 30.dp)
//            .fillMaxWidth()
//    ) {
//
//        // Screen top description
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 25.dp, top = 30.dp, bottom = 20.dp),
//        ) {
//
//            // Selected date indication
//            Row(
//                horizontalArrangement = Arrangement.Start,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    Icons.Rounded.DateRange, "ahs", modifier = Modifier
//                        .padding(end = 24.dp)
//                        .size(35.dp)
//                )
//                Row() {
//
//                    Text(
//                        "Oct",
//                        style = MaterialTheme.typography.headlineLarge,
//                        fontWeight = FontWeight.ExtraBold,
//                        modifier = Modifier.padding(end = 15.dp)
//                    )
//
//                    Text(
//                        text = "2017",
//                        style = MaterialTheme.typography.bodyMedium,
//                    )
//                }
//            }
//
//            // To today text button
//            Text(text = "Today", color = Color.Blue, fontWeight = FontWeight.SemiBold)
//        }
//
//
//        // Notes section
//        Column(modifier = Modifier.fillMaxWidth()) {
//            // Segment indication
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                // Left part
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Start
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .size(width = 15.dp, height = 10.dp)
//                            .clip(RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp))
//                            .background(Color.Blue)
//                    )
//                    Text(
//                        "07:00 - 08:25",
//                        fontWeight = FontWeight.Bold,
//                        style = MaterialTheme.typography.titleMedium,
//                        modifier = Modifier.padding(start = 15.dp)
//                    )
//                }
//
//                // Right part period indication
//                Text(text = "1 h 30 min", color = Color.LightGray)
//            }
//
//
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 30.dp, top = 15.dp)
//                    .border(width = 1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
//                    .padding(15.dp)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(bottom = 5.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .size(5.dp)
//                            .clip(CircleShape)
//                            .background(Color.Blue)
//                    )
//                    Text(
//                        modifier = Modifier.padding(start = 5.dp),
//                        text = "This time I felt great after quick nap...",
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(bottom = 5.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .size(5.dp)
//                            .clip(CircleShape)
//                            .background(Color.Blue)
//                    )
//                    Text(
//                        modifier = Modifier.padding(start = 5.dp),
//                        text = "This time I felt great after quick nap...",
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(bottom = 5.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .size(5.dp)
//                            .clip(CircleShape)
//                            .background(Color.Blue)
//                    )
//                    Text(
//                        modifier = Modifier.padding(start = 5.dp),
//                        text = "This time I felt great after quick nap...",
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(bottom = 5.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .size(5.dp)
//                            .clip(CircleShape)
//                            .background(Color.Blue)
//                    )
//                    Text(
//                        modifier = Modifier.padding(start = 5.dp),
//                        text = "This time I felt great after quick nap...",
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//
//            }
//
//
//            Spacer(modifier = Modifier.size(15.dp))
//
//            // Segment indication
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                // Left part
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Start
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .size(width = 15.dp, height = 10.dp)
//                            .clip(RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp))
//                            .background(Color.Blue)
//                    )
//                    Text(
//                        "07:00 - 08:25",
//                        fontWeight = FontWeight.Bold,
//                        style = MaterialTheme.typography.titleMedium,
//                        modifier = Modifier.padding(start = 15.dp)
//                    )
//                }
//
//                // Right part period indication
//                Text(text = "1 h 30 min", color = Color.LightGray)
//            }
//
//
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 30.dp, top = 15.dp)
//                    .border(width = 1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
//                    .padding(15.dp)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(bottom = 5.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .size(5.dp)
//                            .clip(CircleShape)
//                            .background(Color.Blue)
//                    )
//                    Text(
//                        modifier = Modifier.padding(start = 5.dp),
//                        text = "This time I felt great after quick nap...",
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(bottom = 5.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .size(5.dp)
//                            .clip(CircleShape)
//                            .background(Color.Blue)
//                    )
//                    Text(
//                        modifier = Modifier.padding(start = 5.dp),
//                        text = "This time I felt great after quick nap...",
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//            }
//
//
//
//            Spacer(modifier = Modifier.size(15.dp))
//
//
//            // Segment indication
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                // Left part
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Start
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .size(width = 15.dp, height = 10.dp)
//                            .clip(RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp))
//                            .background(Color.Blue)
//                    )
//                    Text(
//                        "07:00 - 08:25",
//                        fontWeight = FontWeight.Bold,
//                        style = MaterialTheme.typography.titleMedium,
//                        modifier = Modifier.padding(start = 15.dp)
//                    )
//                }
//
//                // Right part period indication
//                Text(text = "1 h 30 min", color = Color.LightGray)
//            }
//
//
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 30.dp, top = 15.dp)
//                    .border(width = 1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
//                    .padding(15.dp)
//            ) {
//                Icon(
//                    Icons.Rounded.PlayArrow,
//                    "ash",
//                    modifier = Modifier.size(35.dp),
//                    tint = Color.LightGray
//                )
//                Text("Add note", color = Color.LightGray)
//            }
//
//            Spacer(modifier = Modifier.size(30.dp))
//        }
//    }
//}
//
