package presentation.ui.chooseSchedule

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import kotlin.math.max
import kotlin.math.roundToInt


class ChooseScheduleScreen : Screen {

    @Composable
    override fun Content() {

        CategoryListContent(
            categoryList = listOf(
                Category("name 1"),
                Category("name 2"),
                Category("name 3"),
                Category("name 4"),
                Category("name 5"),
                Category("name 6"),
                Category("name 7"),
                Category("name 8"),
            )
        )
    }
}


data class Category(val name: String)

@Composable
@Suppress("MagicNumber")
private fun CategoryListContent(
    categoryList: List<Category>,
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
    category: Category,
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
            Image(imageVector = Icons.Outlined.Person, contentDescription = "asht")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = category.name)
        }
    }
}
