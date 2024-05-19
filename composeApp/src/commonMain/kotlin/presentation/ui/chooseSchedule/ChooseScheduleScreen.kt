package presentation.ui.chooseSchedule

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import domain.model.Schedule
import presentation.component.ScheduleComponent
import presentation.ui.home.*
import kotlin.math.max
import kotlin.math.roundToInt


class ChooseScheduleScreen : Screen {

    @Composable
    override fun Content() {

        CategoryListContent(
            categoryList = listOf(
                dualCore1,
                everyman1,
                everyman2,
                segmented,
                siesta,
                dualCore2
            )
        )
    }
}


@Composable
@Suppress("MagicNumber")
private fun CategoryListContent(
    categoryList: List<Schedule>,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier.padding(start = 8.dp, end = 8.dp)) {
        val cellCount: Int = max(2F, maxWidth.value / 250).roundToInt()
        LazyVerticalGrid(columns = GridCells.Fixed(cellCount)) {
            items(
                items = categoryList,
                itemContent = { category ->
                    CategoryItem(category)
                },
            )
        }
    }
}


@Composable
private fun CategoryItem(
    category: Schedule,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable { },
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
        ) {
            ScheduleComponent(category, 100, 50f)
//            Image(imageVector = Icons.Outlined.Person, contentDescription = "asht")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = category.name)
        }
    }
}
